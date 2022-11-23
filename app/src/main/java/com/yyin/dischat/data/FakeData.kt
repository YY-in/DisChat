package com.yyin.dischat.data

import com.yyin.dischat.domain.model.DomainChannel
import com.yyin.dischat.domain.model.DomainGuild
import org.koin.androidx.compose.get


val g1=DomainGuild(1595308033750208512,"linux","https://qiniu.yyin.top/linux.png","https://qiniu.yyin.top/20220407190053.png", listOf(),1,1)
val g2=DomainGuild(2,"bad_face","https://qiniu.yyin.top/20221123130622.png","https://qiniu.yyin.top/20221123140136.png", listOf(),1,1)
val g3=DomainGuild(2,"HelloKitty","https://qiniu.yyin.top/20221123144604.png","https://qiniu.yyin.top/20221123144912.png", listOf(),1,1)

val c1 = DomainChannel.TextChannel(1,1,"技术讨论",1,null, listOf(),false)
val c2 = DomainChannel.VoiceChannel(2,1,"语言聊天",2,null, listOf())

fun getGuildsMap(): Map<Long, DomainGuild>{
    val map = mutableMapOf<Long,DomainGuild>()
    map[0]=g2
    map[1]=g1
    return map
}
fun getGuildById(guildId: Long): DomainGuild{
    var map = getGuildsMap()
    return map[guildId]?:g1
}
fun getChannelsMap(): Map<Long, DomainChannel>{
    val map = mutableMapOf<Long,DomainChannel>()
    map[0]=c1
    map[1]=c2
    return map
}

fun getChannelById(guildId: Long): DomainChannel{
    var map = getChannelsMap()
    return map[guildId]?:c1
}