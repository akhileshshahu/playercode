package com.akhiplayer.akhi.models


import android.net.Uri
import android.support.v4.media.MediaBrowserCompat


data class MediaCategoryInfo(
    val id: MediaId,
    val title: String,
    val subtitle: String,
    val iconUri: Uri,
    val description: String,
    @MediaBrowserCompat.MediaItem.Flags val mediaFlags: Int
)