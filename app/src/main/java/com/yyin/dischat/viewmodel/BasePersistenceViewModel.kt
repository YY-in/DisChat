package com.yyin.dischat.viewmodel

import androidx.lifecycle.ViewModel
import com.yyin.dischat.domain.manager.PersistentDataManager

abstract class BasePersistenceViewModel(
    private val persistentDataManager: PersistentDataManager
) : ViewModel() {
    // TODO: migrate this to a DB like RoomDB

    protected var persistentGuildId
        get() = persistentDataManager.persistentGuildId
        set(value) {
            persistentDataManager.persistentGuildId = value
        }

    protected var persistentChannelId
        get() = persistentDataManager.persistentChannelId
        set(value) {
            persistentDataManager.persistentChannelId = value
        }

    protected var persistentCollapsedCategories
        get() = persistentDataManager.collapsedCategories
        set(value) {
            persistentDataManager.collapsedCategories = value
        }

    protected fun addPersistentCollapseCategory(id: Long) {
        persistentDataManager.addCollapsedCategory(id)
    }

    protected fun removePersistentCollapseCategory(id: Long) {
        persistentDataManager.removeCollapsedCategory(id)
    }
}