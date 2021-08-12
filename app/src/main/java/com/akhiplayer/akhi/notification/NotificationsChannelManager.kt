package com.akhiplayer.akhi.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.odaridavid.akhi.R
import javax.inject.Inject


class NotificationsChannelManager @Inject constructor(
    val context: Context,
    private val notificationManager: NotificationManager
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(channelId: String) {
        val channel = when (channelId) {
            PLAYBACK_CHANNEL_ID -> {
                val name = context.getString(R.string.notification_playback_channel_name)
                val descriptionText =
                    context.getString(R.string.notification_playback_channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                NotificationChannel(channelId, name, importance).apply {
                    description = descriptionText
                }
            }
            else -> throw IllegalArgumentException("Unknown Channel Id Received")
        }
        notificationManager.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun hasChannel(channelId: String): Boolean {
        return notificationManager.getNotificationChannel(channelId) != null
    }

    companion object {
        const val PLAYBACK_CHANNEL_ID = "playback"
    }
}