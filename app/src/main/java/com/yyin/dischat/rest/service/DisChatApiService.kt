package com.yyin.dischat.rest.service

import com.yyin.dischat.BuildConfig
import com.yyin.dischat.rest.body.MessageBody
import com.yyin.dischat.rest.dto.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Service用于数据的请求、缓存
 */
interface DisChatApiService {
    suspend fun getMeGuilds(): List<ApiMeGuild>
    suspend fun getGuild(guildId: Long): ApiGuild
    suspend fun getGuildChannels(guildId: Long): Map<ApiSnowflake, ApiChannel>

    suspend fun getChannel(channelId: Long): ApiChannel
    suspend fun getChannelMessages(channelId: Long): Map<ApiSnowflake, ApiMessage>
    suspend fun getChannelPins(channelId: Long): Map<ApiSnowflake, ApiMessage>

    suspend fun postChannelMessage(channelId: Long, body: MessageBody)

    suspend fun getUserSettings(): ApiUserSettings

    suspend fun startTyping(channelId: Long)
}

class DisChatApiServiceImpl(
    private val client: HttpClient
) : DisChatApiService{


    private val cachedMeGuilds = mutableListOf<ApiMeGuild>()
    private val cachedGuildById = mutableMapOf<Long, ApiGuild>()
    private val cachedGuildChannels = mutableMapOf<Long, MutableMap<ApiSnowflake, ApiChannel>>()
    private val cachedChannelMessages = mutableMapOf<Long, MutableMap<ApiSnowflake, ApiMessage>>()


    override suspend fun getMeGuilds(): List<ApiMeGuild> {
        return withContext(Dispatchers.IO) {
            if (cachedMeGuilds.isEmpty()) {
                val url = getMeGuildsUrl()
                val response: List<ApiMeGuild> = client.get(url).body()
                cachedMeGuilds.addAll(response)
            }
            cachedMeGuilds
        }
    }

    override suspend fun getGuild(guildId: Long): ApiGuild {
        return withContext(Dispatchers.IO) {
            if (cachedGuildById[guildId] == null) {
                val url = getGuildUrl(guildId)

                val response: ApiGuild = client.get(url).body()
                cachedGuildById[guildId] = response
            }
            cachedGuildById[guildId]!!
        }
    }

    override suspend fun getGuildChannels(guildId: Long): Map<ApiSnowflake, ApiChannel> {
        // 配置调度器，让其工作在IO线程
        return withContext(Dispatchers.IO) {
            if (cachedGuildChannels[guildId] == null) {
                val url = getGuildChannelsUrl(guildId)
                // 获取API相应并反序列化
                val response: List<ApiChannel> = client.get(url).body()
                // 获取
                cachedGuildChannels[guildId] = response.associateBy { it.id }.toMutableMap()
            }
            cachedGuildChannels[guildId]!!
        }
    }

    override suspend fun getChannel(channelId: Long): ApiChannel {
        TODO("Not yet implemented")
    }

    override suspend fun getChannelMessages(channelId: Long): Map<ApiSnowflake, ApiMessage> {
        TODO("Not yet implemented")
    }

    override suspend fun getChannelPins(channelId: Long): Map<ApiSnowflake, ApiMessage> {
        TODO("Not yet implemented")
    }

    override suspend fun postChannelMessage(channelId: Long, body: MessageBody) {
        return withContext(Dispatchers.IO) {
            if (cachedChannelMessages[channelId] == null) {
                val url = getChannelMessagesUrl(channelId)
                val response: List<ApiMessage> = client.get(url).body()
                cachedChannelMessages[channelId] = response.associateBy { it.id }.toMutableMap()
            }
            cachedChannelMessages[channelId]!!
        }
    }

    override suspend fun getUserSettings(): ApiUserSettings {
        TODO("Not yet implemented")
    }

    override suspend fun startTyping(channelId: Long) {
        TODO("Not yet implemented")
    }

    // 请求地址的获取
    private companion object {
//        const val BASE = BuildConfig.URL_API
        const val BASE = "http://192.168.43.101:8080"

        fun getMeGuildsUrl(): String {
            return "$BASE/users/guilds"
        }

        fun getGuildUrl(guildId: Long): String {
            return "$BASE/guilds/$guildId"
        }

        fun getGuildChannelsUrl(guildId: Long): String {
            val guildUrl = getGuildUrl(guildId)
            return "$guildUrl/channels"
        }

        fun getChannelUrl(channelId: Long): String {
            return "$BASE/channels/$channelId"
        }

        fun getTypingUrl(channelId: Long): String {
            val channelUrl = getChannelUrl(channelId)
            return "$channelUrl/typing"
        }

        fun getChannelMessagesUrl(channelId: Long): String {
            val channelUrl = getChannelUrl(channelId)
            return "$channelUrl/messages"
        }
    }
}
