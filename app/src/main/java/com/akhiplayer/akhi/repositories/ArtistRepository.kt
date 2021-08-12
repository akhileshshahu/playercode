package com.akhiplayer.akhi.repositories


import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.akhiplayer.akhi.models.Artist


internal class ArtistRepository(val applicationContext: Context) {

    /**
     * Returns a list of artists after extracting from a cursor
     */
    fun loadAllArtists(): List<Artist> {
        val artists = mutableListOf<Artist>()
        val cursor = getArtistCursor() ?: throw IllegalStateException("Artists cursor is null")
        while (cursor.moveToNext()) {
            artists.add(cursor.mapToArtistEntity())
        }
        cursor.close()
        return artists
    }

    private fun Cursor.mapToArtistEntity(): Artist {
        val artistId = getColumnIndexOrThrow(MediaStore.Audio.Artists._ID)
        val artistName = getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST)
        val noOfAlbums = getColumnIndexOrThrow(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)
        val noOfTracks = getColumnIndexOrThrow(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)
        return Artist(
            id = getLong(artistId),
            name = getString(artistName),
            noOfAlbums = getInt(noOfAlbums),
            noOfTracks = getInt(noOfTracks)
        )
    }

    private fun getArtistCursor(): Cursor? {
        val cr = applicationContext.contentResolver
        val uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
        val projection: Array<String> = getArtistColumns()
        val sortOrder = "${MediaStore.Audio.Artists.ARTIST} DESC"
        return cr.query(uri, projection, null, null, sortOrder)
    }

    private fun getArtistColumns(): Array<String> {
        return arrayOf(
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS
        )
    }

}