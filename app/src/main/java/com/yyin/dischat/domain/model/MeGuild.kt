package com.yyin.dischat.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "me_guild")
data class DomainMeGuild(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name="icon_url")val iconUrl: String?,
    val permissions: List<DomainPermission>,
) {
    val iconText = name.split("""\s+""".toRegex()).map { it[0] }.joinToString()
}