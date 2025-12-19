package com.soundlab.soundora.domain.model

data class Track(
    val id: Long = 0L,
    val readable: Boolean = true,
    val title: String = "",
    val titleShort: String = "",
    val titleVersion: String = "",
    val link: String = "",
    val duration: Long = 0L,
    val rank: Long = 0L,
    val explicitLyrics: Boolean = false,
    val explicitContentLyrics: Long = 0L,
    val explicitContentCover: Long = 0L,
    val preview: String = "",
    val md5Image: String = "",
    val artist: Artist = Artist(),
    val album: Album = Album(),
    val type: String = "track"
)
