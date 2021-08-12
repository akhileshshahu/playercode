package com.akhiplayer.akhi.utils

import android.support.v4.media.session.MediaControllerCompat
import com.akhiplayer.akhi.ui.DashboardActivity

internal val DashboardActivity.mediaTranspotControls: MediaControllerCompat.TransportControls?
    get() = MediaControllerCompat.getMediaController(this).transportControls

internal val DashboardActivity.mediaControllerCompat: MediaControllerCompat?
    get() = MediaControllerCompat.getMediaController(this)

