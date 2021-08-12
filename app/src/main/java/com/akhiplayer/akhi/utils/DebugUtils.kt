package com.akhiplayer.akhi.utils


import android.support.v4.media.session.PlaybackStateCompat

//TODO Move to a debug source set
internal object DebugUtils {
    fun getPlaybackState(state: Int): String {
        return when (state) {
            PlaybackStateCompat.STATE_NONE -> "Playback None"
            PlaybackStateCompat.STATE_STOPPED -> "Playback Stopped"
            PlaybackStateCompat.STATE_ERROR -> "Playback Error"
            PlaybackStateCompat.STATE_PLAYING -> "Playback Playing"
            PlaybackStateCompat.STATE_PAUSED -> "Playback Paused"
            PlaybackStateCompat.STATE_SKIPPING_TO_NEXT -> "Playback Skipping To Next"
            PlaybackStateCompat.STATE_BUFFERING -> "Playback Buffering"
            else -> "Unknown Playback State"
        }
    }
}