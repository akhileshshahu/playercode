package com.akhiplayer.akhi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhiplayer.akhi.data.LastPlayedTrackPreference
import com.akhiplayer.akhi.models.PlaybackStatus

internal class DashboardViewModel(private val lastPlayedTrackPreference: LastPlayedTrackPreference) :
    ViewModel() {

    private val _isMediaBrowserConnected = MutableLiveData<Boolean>()
    val isMediaBrowserConnected: LiveData<Boolean>
        get() = _isMediaBrowserConnected

    private val _playbackStatus = MutableLiveData<PlaybackStatus>()
    val playbackStatus: LiveData<PlaybackStatus>
        get() = _playbackStatus

    fun setIsConnected(value: Boolean) {
        _isMediaBrowserConnected.value = value
    }

    fun setNowPlayingStatus(playbackStatus: PlaybackStatus) {
        _playbackStatus.value = playbackStatus
    }

    fun setCurrentlyPlayingTrackId(trackId: Long) {
        lastPlayedTrackPreference.lastPlayedTrackId = trackId
    }

    class Factory(
        private val lastPlayedTrackPreference: LastPlayedTrackPreference
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DashboardViewModel(lastPlayedTrackPreference) as T
        }
    }
}

