package com.yyin.dischat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yyin.dischat.domain.model.DomainGuild

@Dao
interface GuildsDao {
    @Insert
    fun insertAll(vararg guilds:DomainGuild)

    @Query("SELECT *  From me_guild")
    fun loadAll()
}