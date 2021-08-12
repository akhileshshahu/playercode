package com.akhiplayer.akhi.models


internal data class Album(
    val id: Long,
    val title: String,
    val artist: String,
    val noOfSongs: Int,
    val albumArt: String
)