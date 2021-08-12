package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.repositories.GenreRepository
import dagger.Module
import dagger.Provides

@Module
internal class GenreModule {

    @Provides
    fun providesGenreProvider(applicationContext: Context): GenreRepository {
        return GenreRepository(
            applicationContext
        )
    }
}