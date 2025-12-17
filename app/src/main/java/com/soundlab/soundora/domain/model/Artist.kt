package com.soundlab.soundora.domain.model

data class Artist(
    val id: Long = 0L,
    val name: String = "",
    val link: String = "",
    val share: String = "",
    val picture: String = "",
    val pictureSmall: String = "",
    val pictureMedium: String = "",
    val pictureBig: String = "",
    val pictureXl: String = "",
    val nbAlbum: Long = 0L,
    val nbFan: Long = 0L,
    val trackList: String = "",
    val type: String = ""
)