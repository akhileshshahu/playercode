package com.akhiplayer.akhi.repositories


import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.akhiplayer.akhi.models.Album


internal class AlbumRepository(private val applicationContext: Context) {

    /**
     * Returns a list of albums after extracting from a cursor
     */
    fun loadAllAlbums(): List<Album> {
        val albums = mutableListOf<Album>()
        val cursor = getAlbumsCursor() ?: throw IllegalStateException("Albums cursor is null")
        while (cursor.moveToNext()) {
            albums.add(cursor.mapToAlbumEntity())
        }
        cursor.close()
        return albums
    }

    /**
     * Returns a filtered list of albums based on the query after extracting from a cursor
     */
    fun loadAlbumsByQuery(selection: String, selectionArgs: Array<String>): List<Album> {
        val albums = mutableListOf<Album>()
        val cursor = getAlbumsCursor(selection, selectionArgs)
            ?: throw IllegalStateException("Albums cursor is null")
        if (cursor.count != 0) {
            cursor.moveToFirst()
            do {
                albums.add(cursor.mapToAlbumEntity())
            } while (cursor.moveToNext())
        }
        cursor.close()
        return albums
    }

    /**
     * Convert cursor rows to an album entity
     */
    private fun Cursor.mapToAlbumEntity(): Album {
        val id = getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)
        val albumTitle = getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
        val albumArtist = getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST)
        val noOfSongs = getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS)
        val albumArt = getAlbumArtPath(getLong(id))
        return Album(
            id = getLong(id),
            title = getString(albumTitle),
            artist = getString(albumArtist),
            noOfSongs = getInt(noOfSongs),
            albumArt = albumArt
        )
    }

    private fun getAlbumArtPath(id: Long): String {
        val artworkUri = Uri.parse("content://media/external/audio/albumart")
        return Uri.withAppendedPath(artworkUri, "$id").toString()
    }

    /**
     * Loads information on albums using a content resolver and returns a cursor object
     */
    private fun getAlbumsCursor(
        selection: String? = null,
        selectionArgs: Array<String>? = null
    ): Cursor? {
        val cr = applicationContext.contentResolver
        val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val projection: Array<String> = getAlbumColumns()
        val sortOrder = "${MediaStore.Audio.Albums.LAST_YEAR} DESC"
        return cr.query(uri, projection, selection, selectionArgs, sortOrder)
    }

    private fun getAlbumColumns(): Array<String> {
        return arrayOf(
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS
        )
    }

}