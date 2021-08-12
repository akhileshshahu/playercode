package com.akhiplayer.akhi.models


internal data class Track(
    val id: Long,
    val artistId: Long,
    val title: String,
    val album: String,
    val artist: String,
    val displayName: String,
    val track: String,
    val duration: String,
    val filePath: String,
    val albumArt: String
)