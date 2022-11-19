package com.yyin.dischat.domain.manager

import android.content.SharedPreferences

interface AccountManager {

    var currentAccountToken: String?

    val isLoggedIn: Boolean

}

class AccountManagerImpl(
    authPrefs: SharedPreferences
) : BasePreferenceManager(authPrefs), AccountManager {

    override var currentAccountToken: String?
        get() = getString(USER_TOKEN_KEY, null)
        set(value) {
            putString(USER_TOKEN_KEY, value)
        }

    override val isLoggedIn: Boolean
        get() = currentAccountToken != null

    companion object {
        const val USER_TOKEN_KEY = "user_token"
    }

}