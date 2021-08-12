package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.repositories.AlbumRepository
import dagger.Module
import dagger.Provides

/**
 * Creats Album Module Dependencies
 */
@Module
internal class AlbumModule {

    @Provides
    fun providesAlbumProvider(applicationContext: Context): AlbumRepository {
        return AlbumRepository(
            applicationContext
        )
    }
}