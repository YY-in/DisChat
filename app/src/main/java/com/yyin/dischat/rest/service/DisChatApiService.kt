package com.yyin.dischat.rest.service

import com.yyin.dischat.BuildConfig
import com.yyin.dischat.rest.dto.ApiChannel
import com.yyin.dischat.rest.dto.ApiSnowflake
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Service用于数据的请求、缓存
 */
interface DisChatApiService {
    suspend fun getGuildChannels(guildId: Long): Map<ApiSnowflake, ApiChannel>
}

class DisChatApiServiceImpl(
    private val client: HttpClient
) : DisChatApiService{

    private val cachedGuildChannels = mutableMapOf<Long, MutableMap<ApiSnowflake, ApiChannel>>()

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

    // 请求地址的获取
    private companion object {
        const val BASE = BuildConfig.URL_API

        fun getGuildUrl(guildId: Long): String {
            return "$BASE/guilds/$guildId"
        }

        fun getGuildChannelsUrl(guildId: Long): String {
            val guildUrl = getGuildUrl(guildId)
            return "$guildUrl/channels"
        }
    }
}
