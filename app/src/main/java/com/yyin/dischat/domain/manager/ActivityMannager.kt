package com.yyin.dischat.domain.manager

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.yyin.dischat.ui.LandingActivity

interface ActivityManager {

    fun startMainActivity()

}

class ActivityManagerImpl(
    private val context: Context
) : ActivityManager {

    override fun startMainActivity() {
        startActivity<LandingActivity>()
    }

    private inline fun <reified T : Activity> startActivity() {
        context.startActivity(
            Intent(context, T::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}
