package com.akhiplayer.akhi.repositories

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.akhiplayer.akhi.models.Genre


internal class GenreRepository(val applicationContext: Context) {

    /**
     * Returns a list of genres after extracting from a cursor
     */
    fun loadAllGenres(): List<Genre> {
        val genres = mutableListOf<Genre>()
        val cursor = getGenreCursor() ?: throw IllegalStateException("Genres cursor is null")
        while (cursor.moveToNext()) {
            genres.add(cursor.mapToGenreEntity())
        }
        cursor.close()
        return genres
    }

    private fun Cursor.mapToGenreEntity(): Genre {
        val genreName = getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME)
        val genreId = getColumnIndexOrThrow(MediaStore.Audio.Genres._ID)
        return Genre(
            id = getLong(genreId),
            name = getString(genreName)
        )
    }

    private fun getGenreCursor(): Cursor? {
        val cr = applicationContext.contentResolver
        val uri = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI
        val projection: Array<String> = getGenreColumns()
        val sortOrder = "${MediaStore.Audio.Genres.NAME} DESC"
        return cr.query(uri, projection, null, null, sortOrder)
    }

    private fun getGenreColumns(): Array<String> {
        return arrayOf(
            MediaStore.Audio.Genres._ID,
            MediaStore.Audio.Genres.NAME
        )
    }
}