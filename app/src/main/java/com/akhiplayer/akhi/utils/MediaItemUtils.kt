package com.akhiplayer.akhi.utils

import android.content.Context
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat

fun createMediaItem(
    title: String,
    subtitle: String,
    mediaItemId: String,
    iconUri: Uri?,
    description: String,
    @MediaBrowserCompat.MediaItem.Flags mediaItemFlags: Int
): MediaBrowserCompat.MediaItem {
    return MediaBrowserCompat.MediaItem(
        MediaDescriptionCompat.Builder()
            .setMediaId(mediaItemId)
            .setTitle(title)
            .setSubtitle(subtitle)
            .setIconUri(iconUri)
            .setMediaUri(null)
            .setDescription(description)
            .build(),
        mediaItemFlags
    )
}

fun getDrawableUri(context: Context, drawableName: String): Uri {
    val appPackageName = context.packageName
    return Uri.parse("android.resource://$appPackageName/drawable/$drawableName")
}

fun convertMediaIdToTrackId(mediaId: String): Long {
    val spIndex = mediaId.indexOf('-')
    return mediaId.substring(spIndex + 1).toLong()
}
