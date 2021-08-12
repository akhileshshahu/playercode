package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.playback.session.MediaLoader
import com.akhiplayer.akhi.playback.player.TrackPlayer
import com.akhiplayer.akhi.repositories.AlbumRepository
import com.akhiplayer.akhi.repositories.ArtistRepository
import com.akhiplayer.akhi.repositories.GenreRepository
import com.akhiplayer.akhi.repositories.PlaylistRepository
import com.akhiplayer.akhi.repositories.TrackRepository
import dagger.Module
import dagger.Provides

@Module
internal class PlaybackModule {

    @Provides
    fun providesMediaLoader(
        context: Context,
        albumRepository: AlbumRepository,
        artistRepository: ArtistRepository,
        genreRepository: GenreRepository,
        trackRepository: TrackRepository,
        playlistRepository: PlaylistRepository
    ): MediaLoader {
        return MediaLoader(
            context,
            albumRepository,
            artistRepository,
            genreRepository,
            trackRepository,
            playlistRepository
        )
    }

    @Provides
    fun providesTrackPlayer(context: Context, trackRepository: TrackRepository): TrackPlayer {
        return TrackPlayer(
            context,
            trackRepository
        )
    }

}