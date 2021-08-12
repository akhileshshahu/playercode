package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.repositories.PlaylistRepository
import dagger.Module
import dagger.Provides


@Module
internal class PlaylistModule {

    @Provides
    fun providesPlaylistProvider(applicationContext: Context): PlaylistRepository {
        return PlaylistRepository(
            applicationContext
        )
    }
}