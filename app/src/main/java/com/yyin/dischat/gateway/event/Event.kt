package com.yyin.dischat.gateway.event


import com.xinto.opencord.gateway.event.*
import com.yyin.dischat.gateway.io.EventName
import com.yyin.dischat.gateway.dto.MessageDeleteData
import com.yyin.dischat.gateway.dto.Ready
import com.yyin.dischat.gateway.dto.SessionData
import com.yyin.dischat.rest.dto.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonElement

interface Event

class EventDeserializationStrategy(
    private val eventName: EventName
) : DeserializationStrategy<Event> {

    override val descriptor: SerialDescriptor
        get() = JsonElement.serializer().descriptor

    override fun deserialize(decoder: Decoder): Event {
        return when (eventName) {
            EventName.Ready -> {
                ReadyEvent(
                    data = decoder.decodeSerializableValue(Ready.serializer())
                )
            }
            EventName.UserUpdate -> {
                UserUpdateEvent(
                    data = decoder.decodeSerializableValue(ApiUser.serializer())
                )
            }
            EventName.GuildMemberChunk -> {
                GuildMemberChunkEvent(
                    data = decoder.decodeSerializableValue(ApiGuildMemberChunk.serializer())
                )
            }
            EventName.GuildCreate -> {
                GuildCreateEvent(
                    data = decoder.decodeSerializableValue(ApiGuild.serializer())
                )
            }
            EventName.GuildUpdate -> {
                GuildUpdateEvent(
                    data = decoder.decodeSerializableValue(ApiGuild.serializer())
                )
            }
            EventName.GuildDelete -> {
                TODO()
            }
            EventName.ChannelCreate -> {
                ChannelCreateEvent(
                    data = decoder.decodeSerializableValue(ApiChannel.serializer())
                )
            }
            EventName.ChannelUpdate -> {
                ChannelUpdateEvent(
                    data = decoder.decodeSerializableValue(ApiChannel.serializer())
                )
            }
            EventName.ChannelDelete -> {
                ChannelDeleteEvent(
                    data = decoder.decodeSerializableValue(ApiChannel.serializer())
                )
            }
            EventName.MessageCreate -> {
                MessageCreateEvent(
                    data = decoder.decodeSerializableValue(ApiMessage.serializer())
                )
            }
            EventName.MessageUpdate -> {
                MessageUpdateEvent(
                    data = decoder.decodeSerializableValue(ApiMessagePartial.serializer())
                )
            }
            EventName.MessageDelete -> {
                MessageDeleteEvent(
                    data = decoder.decodeSerializableValue(MessageDeleteData.serializer())
                )
            }
            EventName.SessionsReplace -> {
                SessionsReplaceEvent(
                    data = decoder.decodeSerializableValue(ListSerializer(SessionData.serializer()))
                )
            }
            EventName.UserSettingsUpdate -> {
                UserSettingsUpdateEvent(
                    data = decoder.decodeSerializableValue(ApiUserSettingsPartial.serializer())
                )
            }
            else -> throw IllegalArgumentException("Unknown event $eventName")
        }
    }
}