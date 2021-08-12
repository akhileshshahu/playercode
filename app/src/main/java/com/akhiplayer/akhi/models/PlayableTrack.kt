package com.akhiplayer.akhi.models


import android.net.Uri

/**
 * Track displayed on the UI
 */
data class PlayableTrack(
    val mediaId: String?,
    val title: String?,
    val artist: String,
    val icon: Uri?,
    val isPlaying: Boolean = false
)