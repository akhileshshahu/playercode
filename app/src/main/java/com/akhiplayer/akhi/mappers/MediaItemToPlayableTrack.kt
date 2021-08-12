package com.akhiplayer.akhi.mappers


import android.support.v4.media.MediaBrowserCompat
import com.akhiplayer.akhi.models.PlayableTrack


fun MediaBrowserCompat.MediaItem.toTrack(): PlayableTrack {
    return PlayableTrack(
        this.mediaId,
        this.description.title.toString(),
        this.description.subtitle.toString(),
        this.description.iconUri
    )
}
