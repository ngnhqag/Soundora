package com.soundlab.soundora.data.remote.model

import com.google.gson.annotations.SerializedName

data class TopAlbumDto(
    val id: Long,
    val title: String,
    val link: String,
    val cover: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("record_type")
    val recordType: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    val position: Long,
    val artist: ArtistDto,
    val type: String
)
