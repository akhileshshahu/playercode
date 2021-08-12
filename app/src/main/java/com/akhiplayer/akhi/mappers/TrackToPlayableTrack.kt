package com.akhiplayer.akhi.mappers

import androidx.core.net.toUri
import com.akhiplayer.akhi.models.MediaId
import com.akhiplayer.akhi.models.PlayableTrack
import com.akhiplayer.akhi.models.Track


internal fun Track.toPlayableTrack(): PlayableTrack {
    val mediaId = "${MediaId.TRACK}-${id}"

    return PlayableTrack(
        mediaId,
        title,
        artist,
        albumArt.toUri()
    )
}