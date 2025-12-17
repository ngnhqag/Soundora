package com.soundlab.soundora.domain.model

data class Album(
    val id: Long = 0L,
    val title: String = "",
    val link: String = "",
    val share: String = "",
    val cover: String = "",
    val coverSmall: String = "",
    val coverMedium: String = "",
    val coverBig: String = "",
    val coverXl: String = "",
    val md5Image: String = "",
    val genreId: Long = 0L,
    val nbTracks: Long = 0L,
    val duration: Long = 0L,
    val fans: Long = 0L,
    val releaseDate: String = "",
    val recordType: String = "",
    val trackList: String = "",
    val explicitLyrics: Boolean = false,
    val artist: Artist = Artist(),
    val type: String = ""
)
