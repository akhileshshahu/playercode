package com.akhiplayer.akhi.playback.session

import android.content.Context
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import com.github.odaridavid.akhi.R
import com.akhiplayer.akhi.models.MediaCategoryInfo
import com.akhiplayer.akhi.models.MediaId
import com.akhiplayer.akhi.repositories.AlbumRepository
import com.akhiplayer.akhi.repositories.ArtistRepository
import com.akhiplayer.akhi.repositories.GenreRepository
import com.akhiplayer.akhi.repositories.PlaylistRepository
import com.akhiplayer.akhi.repositories.TrackRepository
import com.akhiplayer.akhi.utils.convertMillisecondsToDuration
import com.akhiplayer.akhi.utils.createMediaItem
import com.akhiplayer.akhi.utils.getDrawableUri


internal class MediaLoader(
    private val context: Context,
    private val albumRepository: AlbumRepository,
    private val artistRepository: ArtistRepository,
    private val genreRepository: GenreRepository,
    private val trackRepository: TrackRepository,
    private val playlistRepository: PlaylistRepository
) {

    fun getMediaItemChildren(
        mediaItems: MutableList<MediaBrowserCompat.MediaItem>,
        mediaId: MediaId?
    ) {
        if (mediaId != null)
            when (mediaId) {
                MediaId.ALBUM -> {
                    val albums = albumRepository.loadAllAlbums()
                    albums.forEach { album ->
                        mediaItems.add(
                            createMediaItem(
                                album.title,
                                context.resources.getQuantityString(
                                    R.plurals.number_of_songs,
                                    album.noOfSongs,
                                    album.noOfSongs
                                ),
                                "${MediaId.ALBUM}-${album.id}",
                                null,
                                "",
                                MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                            )
                        )
                    }
                }
                MediaId.ARTIST -> {
                    val artists = artistRepository.loadAllArtists()
                    artists.forEach { artist ->
                        mediaItems.add(
                            createMediaItem(
                                artist.name,
                                context.resources.getQuantityString(
                                    R.plurals.number_of_albums,
                                    artist.noOfAlbums,
                                    artist.noOfAlbums
                                ),
                                "${MediaId.ARTIST}-${artist.id}",
                                null,
                                "",
                                MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                            )
                        )
                    }
                }
                MediaId.GENRE -> {
                    val genres = genreRepository.loadAllGenres()
                    genres.forEach { genre ->
                        mediaItems.add(
                            createMediaItem(
                                genre.name,
                                "",
                                "${MediaId.GENRE}-${genre.id}",
                                null,
                                "",
                                MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                            )
                        )

                    }
                }
                MediaId.PLAYLIST -> {
                    val playlists = playlistRepository.loadAllPlaylists()
                    playlists.forEach { playlist ->
                        mediaItems.add(
                            createMediaItem(
                                playlist.name,
                                playlist.modified,
                                "${MediaId.PLAYLIST}-${playlist.id}",
                                null,
                                "",
                                MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                            )
                        )
                    }
                }
                MediaId.TRACK -> {
                    val tracks = trackRepository.loadAllTracks()
                    tracks.forEach { track ->
                        mediaItems.add(
                            createMediaItem(
                                track.title,
                                track.artist,
                                "${MediaId.TRACK}-${track.id}",
                                Uri.parse(track.albumArt),
                                convertMillisecondsToDuration(track.duration.toLong()),
                                MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
                            )
                        )
                    }
                }
            }
    }


    fun buildMediaCategories(mediaItems: MutableList<MediaBrowserCompat.MediaItem>) {
        with(context) {
            val mediaCategoriesInfo =
                mutableListOf(
                    MediaCategoryInfo(
                        MediaId.TRACK,
                        getString(R.string.title_tracks),
                        getString(R.string.subtitle_all_tracks),
                        getDrawableUri(this, "ic_tracks_black_24dp"),
                        "",
                        MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
                    ),
                    MediaCategoryInfo(
                        MediaId.ALBUM,
                        getString(R.string.title_albums),
                        getString(R.string.subtitle_all_albums),
                        getDrawableUri(this, "ic_album_black_24dp"),
                        "",
                        MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                    ),
                    MediaCategoryInfo(
                        MediaId.ARTIST,
                        getString(R.string.title_artists),
                        getString(R.string.subtitle_all_artists),
                        getDrawableUri(this, "ic_artist_black_24dp"),
                        "",
                        MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                    ),
                    MediaCategoryInfo(
                        MediaId.PLAYLIST,
                        getString(R.string.title_playlists),
                        getString(R.string.subtitle_all_playlists),
                        getDrawableUri(this, "ic_playlist_black_24dp"),
                        "",
                        MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                    ),
                    MediaCategoryInfo(
                        MediaId.GENRE,
                        getString(R.string.title_genres),
                        getString(R.string.subtitle_all_genres),
                        getDrawableUri(this, "ic_genres_black_24dp"),
                        "",
                        MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                    )
                )

            for (mediaCategoryInfo in mediaCategoriesInfo) {
                mediaItems.add(
                    createMediaItem(
                        mediaCategoryInfo.title,
                        mediaCategoryInfo.subtitle,
                        mediaCategoryInfo.id.toString(),
                        mediaCategoryInfo.iconUri,
                        mediaCategoryInfo.description,
                        mediaCategoryInfo.mediaFlags
                    )
                )
            }
        }
    }
}