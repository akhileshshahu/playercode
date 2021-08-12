package com.akhiplayer.akhi.playback

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.support.v4.media.session.MediaControllerCompat
import javax.inject.Inject


class BecomingNoisyReceiver @Inject constructor(private val mediaControllerCompat: MediaControllerCompat) :
    BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
            mediaControllerCompat.transportControls.pause()
        }
    }
}