package com.akhiplayer.akhi.di

import android.content.SharedPreferences
import com.akhiplayer.akhi.data.LastPlayedTrackPreference
import dagger.Module
import dagger.Provides


@Module
internal class DataModule {

    @Provides
    fun providesLastPlayedTrackPreference(sharedPreferences: SharedPreferences): LastPlayedTrackPreference {
        return LastPlayedTrackPreference(sharedPreferences)
    }
}