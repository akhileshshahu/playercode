package com.akhiplayer.akhi.repositories


import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.akhiplayer.akhi.models.Playlist

internal class PlaylistRepository(val applicationContext: Context) {

    /**
     * Returns a list of playlists after extracting from a cursor
     */
    fun loadAllPlaylists(): List<Playlist> {
        val playlists = mutableListOf<Playlist>()
        val cursor = getPlaylistCursor() ?: throw IllegalStateException("Playlists cursor is null")
        while (cursor.moveToNext()) {
            playlists.add(cursor.mapToPlaylistEntity())
        }
        cursor.close()
        return playlists
    }

    private fun Cursor.mapToPlaylistEntity(): Playlist {
        val playlistId = getColumnIndexOrThrow(MediaStore.Audio.Playlists._ID)
        val playlistName = getColumnIndexOrThrow(MediaStore.Audio.Playlists.NAME)
        val playlistLastModifiedDate =
            getColumnIndexOrThrow(MediaStore.Audio.Playlists.DATE_MODIFIED)
        return Playlist(
            id = getLong(playlistId),
            name = getString(playlistName),
            modified = getString(playlistLastModifiedDate)
        )
    }

    private fun getPlaylistCursor(): Cursor? {
        val cr = applicationContext.contentResolver
        val uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI
        val projection: Array<String> = getPlaylistColumns()
        val sortOrder = "${MediaStore.Audio.Playlists.NAME} DESC"
        return cr.query(uri, projection, null, null, sortOrder)
    }

    private fun getPlaylistColumns(): Array<String> {
        return arrayOf(
            MediaStore.Audio.Playlists._ID,
            MediaStore.Audio.Playlists.NAME,
            MediaStore.Audio.Playlists.DATE_MODIFIED
        )
    }
}