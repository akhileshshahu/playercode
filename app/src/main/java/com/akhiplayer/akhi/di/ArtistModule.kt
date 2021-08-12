package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.repositories.ArtistRepository
import dagger.Module
import dagger.Provides


@Module
internal class ArtistModule {

    @Provides
    fun providesArtistsProvider(applicationContext: Context): ArtistRepository {
        return ArtistRepository(
            applicationContext
        )
    }
}