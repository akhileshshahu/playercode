package com.akhiplayer.akhi.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.media.MediaMetadataCompat
import androidx.core.net.toUri
import com.github.odaridavid.akhi.R
import java.io.FileNotFoundException


inline val MediaMetadataCompat.id: String
    get() = getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)

inline val MediaMetadataCompat.title: String?
    get() = getString(MediaMetadataCompat.METADATA_KEY_TITLE)

inline val MediaMetadataCompat.artist: String?
    get() = getString(MediaMetadataCompat.METADATA_KEY_ARTIST)

inline val MediaMetadataCompat.duration
    get() = getLong(MediaMetadataCompat.METADATA_KEY_DURATION)

inline val MediaMetadataCompat.album: String?
    get() = getString(MediaMetadataCompat.METADATA_KEY_ALBUM)

inline val MediaMetadataCompat.albumArtUri: Uri
    get() = this.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI).toUri()

inline val MediaMetadataCompat.albumArt: Bitmap?
    get() = getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART)


fun getAlbumArtBitmap(context: Context, imageUri: Uri): Bitmap? {
    val cr = context.contentResolver
    return try {
        if (versionFrom(Build.VERSION_CODES.P)) {
            val src = ImageDecoder.createSource(cr, imageUri)
            ImageDecoder.decodeBitmap(src)
        } else MediaStore.Images.Media.getBitmap(cr, imageUri)
    } catch (e: FileNotFoundException) {
        BitmapFactory.decodeResource(context.resources, R.drawable.bg_no_art)
    }
}

