package com.yyin.dischat.domain.repository

import com.yyin.dischat.domain.mapper.toDomain
import com.yyin.dischat.domain.model.*
import com.yyin.dischat.rest.body.MessageBody
import com.yyin.dischat.rest.service.DisChatApiService

/**
 *  Repository用于将服务的数据进行包装成对象传递
 */
interface DisChatApiRepository {
    suspend fun getMeGuilds(): List<DomainMeGuild>
    suspend fun getGuild(guildId: Long): DomainGuild
    suspend fun getGuildChannels(guildId: Long): Map<Long, DomainChannel>

    suspend fun getChannel(channelId: Long): DomainChannel
    suspend fun getChannelMessages(channelId: Long): Map<Long, DomainMessage>
    suspend fun getChannelPins(channelId: Long): Map<Long, DomainMessage>

    suspend fun postChannelMessage(channelId: Long, body: MessageBody)

    suspend fun getUserSettings(): DomainUserSettings
    suspend fun updateUserSettings(settings: DomainUserSettingsPartial): DomainUserSettings

    suspend fun startTyping(channelId: Long)
}

class DisChatApiRepositoryImpl(
    private val service: DisChatApiService
) : DisChatApiRepository {

    override suspend fun getMeGuilds(): List<DomainMeGuild> {
        return service.getMeGuilds().map { it.toDomain() }
    }


    override suspend fun getGuild(guildId: Long): DomainGuild {
        return service.getGuild(guildId).toDomain()
    }

    override suspend fun getGuildChannels(guildId: Long): Map<Long, DomainChannel> {
        return service.getGuildChannels(guildId)
            .toList().associate {
                it.first.value to it.second.toDomain()
            }
    }

    override suspend fun getChannel(channelId: Long): DomainChannel {
        return service.getChannel(channelId).toDomain()
    }

    override suspend fun getChannelMessages(channelId: Long): Map<Long, DomainMessage> {
        return service.getChannelMessages(channelId)
            .toList().associate {
                it.first.value to it.second.toDomain()
            }
    }

    override suspend fun getChannelPins(channelId: Long): Map<Long, DomainMessage> {
        return service.getChannelPins(channelId)
            .toList().associate {
                it.first.value to it.second.toDomain()
            }
    }

    override suspend fun postChannelMessage(channelId: Long, body: MessageBody) {
        service.postChannelMessage(channelId, body)
    }

    override suspend fun getUserSettings(): DomainUserSettings {
        return service.getUserSettings().toDomain()
    }

    override suspend fun updateUserSettings(settings: DomainUserSettingsPartial): DomainUserSettings {
        TODO("Not yet implemented")
    }


    override suspend fun startTyping(channelId: Long) {
        service.startTyping(channelId)
    }
}
