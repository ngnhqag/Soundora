package com.soundlab.soundora.data.remote.model.dto

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    val id: Long?,
    val name: String?,
    val link: String?,
    val share: String?,
    val picture: String?,
    @SerializedName("picture_small")
    val pictureSmall: String?,
    @SerializedName("picture_medium")
    val pictureMedium: String?,
    @SerializedName("picture_big")
    val pictureBig: String?,
    @SerializedName("picture_xl")
    val pictureXl: String?,
    @SerializedName("nb_album")
    val nbAlbum: Long?,
    @SerializedName("nb_fan")
    val nbFan: Long?,
    @SerializedName("tracklist")
    val trackList: String?,
    val type: String?,
)