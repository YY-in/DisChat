package com.yyin.dischat.domain.repository

import com.yyin.dischat.domain.mapper.toDomain
import com.yyin.dischat.domain.model.DomainChannel
import com.yyin.dischat.rest.service.DisChatApiService

/**
 *  Repository用于将服务的数据进行包装成对象传递
 */
interface DisChatApiRepository {
    suspend fun getGuildChannels(guildId: Long): Map<Long, DomainChannel>
}


class DisChatApiRepositoryImpl(
    private val service: DisChatApiService
) : DisChatApiRepository {

    override suspend fun getGuildChannels(guildId: Long): Map<Long, DomainChannel> {
        TODO("Not yet implemented")
    }


}