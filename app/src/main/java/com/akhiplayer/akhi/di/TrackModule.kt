package com.akhiplayer.akhi.di

import android.content.Context
import com.akhiplayer.akhi.repositories.AlbumRepository
import com.akhiplayer.akhi.repositories.TrackRepository
import dagger.Module
import dagger.Provides


@Module
internal class TrackModule {

    @Provides
    fun providesTrackProvider(context: Context, albumRepository: AlbumRepository): TrackRepository {
        return TrackRepository(
            context,
            albumRepository
        )
    }

}