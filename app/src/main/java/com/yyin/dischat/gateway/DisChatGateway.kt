package com.yyin.dischat.gateway

import com.yyin.dischat.BuildConfig
import com.yyin.dischat.domain.manager.AccountManager
import com.yyin.dischat.domain.provider.PropertyProvider
import com.yyin.dischat.gateway.dto.*
import com.yyin.dischat.gateway.event.Event
import com.yyin.dischat.gateway.event.EventDeserializationStrategy
import com.yyin.dischat.gateway.event.ReadyEvent
import com.yyin.dischat.gateway.io.IncomingPayload
import com.yyin.dischat.gateway.io.OpCode
import com.yyin.dischat.gateway.io.OutgoingPayload
import com.yyin.dischat.rest.dto.ApiSnowflake
import com.yyin.dischat.util.Logger
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import java.io.ByteArrayOutputStream
import java.util.zip.Inflater
import java.util.zip.InflaterOutputStream
import kotlin.coroutines.CoroutineContext

interface DisChatGateway : CoroutineScope {
    sealed interface State {
        object Started : State
        object Connected : State
        object Disconnected : State
        object Stopped : State
    }

    val events: SharedFlow<Event>
    val state: SharedFlow<State>

    suspend fun connect()
    suspend fun disconnect()

    fun getSessionId(): String

    suspend fun requestGuildMembers(guildId: Long)
    suspend fun updatePresence(presence: UpdatePresence)
}

class DisChatGatewayImpl(
    private val client: HttpClient,
    private val json: Json,
    private val accountManager: AccountManager,
    private val propertyProvider: PropertyProvider,
    private val logger: Logger
) : DisChatGateway {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default

    private lateinit var webSocketSession: DefaultClientWebSocketSession
    private lateinit var zlibInflater: Inflater
    private var establishConnection = true

    private val _events = MutableSharedFlow<Event>()
    override val events = _events.asSharedFlow()

    private val _state = MutableSharedFlow<DisChatGateway.State>()
    override val state = _state.asSharedFlow()

    private var sequenceNumber: Int = 0
    private lateinit var sessionId: String

    override suspend fun connect() {
        _state.emit(DisChatGateway.State.Started)

        while (establishConnection) {
            try {
                establishConnection = false

                zlibInflater = Inflater()
                webSocketSession = client.webSocketSession(BuildConfig.URL_GATEWAY)
                sendIdentification()
                _state.emit(DisChatGateway.State.Connected)
                listenToSocket()

                val reason = withTimeoutOrNull(1500L) {
                    webSocketSession.closeReason.await()
                } ?: return
                val closeCode = CloseCode.fromValue(reason.code.toInt())
                establishConnection = closeCode.canReconnect
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        _state.emit(DisChatGateway.State.Stopped)
    }

    override suspend fun disconnect() {
        logger.debug("Gateway", "Disconnecting")
        webSocketSession.close()
        _state.emit(DisChatGateway.State.Disconnected)
    }

    override fun getSessionId(): String {
        return sessionId
    }

    private suspend fun listenToSocket() {
        webSocketSession.incoming.receiveAsFlow().buffer(Channel.UNLIMITED).map { frame ->
            val jsonString = when (frame) {
                is Frame.Text -> frame.readText()
                is Frame.Binary -> {
                    val deflatedStream = ByteArrayOutputStream()
                    InflaterOutputStream(deflatedStream, zlibInflater)
                        .use { it.write(frame.data) }
                    deflatedStream.use { String(it.toByteArray()) }
                }
                else -> null
            }
            jsonString?.let { str ->
                logger.debug("Gateway", "Inbound: $str")
                try {
                    json.decodeFromString<IncomingPayload>(str)
                } catch (e: Exception) {
//                    e.printStackTrace()
                    null
                }
            }
        }.filterNotNull().collect { incomingPayload ->
            val (opCode, data, seqNum, eventName) = incomingPayload

            if (seqNum != null)
                sequenceNumber = seqNum

            when (opCode) {
                OpCode.Dispatch -> {
                    try {
                        json.decodeFromJsonElement(
                            EventDeserializationStrategy(eventName!!),
                            data!!
                        ).let { decodedEvent ->
                            if (decodedEvent is ReadyEvent) {
                                sessionId = decodedEvent.data.sessionId
                            }
                            _events.emit(decodedEvent)
                        }
                    } catch (e: Exception) {
//                        e.printStackTrace()
                    }
                }
                OpCode.Heartbeat -> {}
                OpCode.Reconnect -> {}
                OpCode.Hello -> {
                    launch {
                        val interval =
                            json.decodeFromJsonElement<Heartbeat>(data!!).heartbeatInterval
                        runHeartbeat(interval, initial = true)
                    }
                }
                OpCode.InvalidSession -> {
                    val canResume = json.decodeFromJsonElement<Boolean>(data!!)
                    if (canResume) {
                        sendResume()
                    }
                    logger.debug("Gateway", "Invalid Session, canResume: $canResume")
                }
                OpCode.HeartbeatAck -> {
                    logger.info("Gateway", "Heartbeat Acked!")
                }
                else -> {}
            }
        }
    }

    private tailrec suspend fun runHeartbeat(
        interval: Long,
        initial: Boolean = false
    ) {
        val delay = if (initial) (interval * Math.random()).toLong() else interval
        delay(delay)
        sendHeartbeat()
        runHeartbeat(interval)
    }

    private suspend fun sendHeartbeat() {
        sendPayload(
            opCode = OpCode.Heartbeat,
            data = sequenceNumber
        )
    }

    private suspend fun sendIdentification() {
        sendPayload(
            opCode = OpCode.Identify,
            data = Identification(
                token = accountManager.currentAccountToken!!,
                capabilities = 95,
                largeThreshold = 100,
                compress = true,
                properties = propertyProvider.identificationProperties,
                clientState = IdentificationClientState(
                    guildHashes = emptyMap(),
                    highestLastMessageId = 0,
                    readStateVersion = -1,
                    userGuildSettingsVersion = -1
                ),
            )
        )
    }

    private suspend fun sendResume() {
        sendPayload(
            opCode = OpCode.Resume,
            data = Resume(
                token = accountManager.currentAccountToken!!,
                sessionId = sessionId,
                sequenceNumber = sequenceNumber
            )
        )
    }

    private suspend inline fun <reified T> sendPayload(opCode: OpCode, data: T?) {
        sendSerializedData(
            OutgoingPayload(
                opCode = opCode,
                data = data
            )
        )
    }

    private suspend inline fun <reified T> sendSerializedData(data: T) {
        val json = json.encodeToString(data)
        logger.debug("Gateway", "Outbound: $json")
        webSocketSession.send(Frame.Text(json))
    }

    override suspend fun requestGuildMembers(guildId: Long) {
        sendPayload(
            opCode = OpCode.RequestGuildMembers,
            data = RequestGuildMembers(
                guildId = ApiSnowflake(guildId)
            )
        )
    }

    override suspend fun updatePresence(presence: UpdatePresence) {
        sendPayload(
            opCode = OpCode.PresenceUpdate,
            data = presence,
        )
    }
}

inline fun <reified E : Event> DisChatGateway.onEvent(
    noinline filterPredicate: suspend (E) -> Boolean = { true },
    crossinline block: suspend (E) -> Unit
) {
    events.buffer(Channel.UNLIMITED)
        .filterIsInstance<E>()
        .filter(filterPredicate)
        .onEach {
            launch {
                block(it)
            }
        }.launchIn(this)
}

inline fun DisChatGateway.scheduleOnConnection(
    crossinline block: suspend () -> Unit
) {
    state.buffer(Channel.UNLIMITED)
        .onEach {
            if (it is DisChatGateway.State.Connected) {
                block()
            }
        }.launchIn(this)
}
