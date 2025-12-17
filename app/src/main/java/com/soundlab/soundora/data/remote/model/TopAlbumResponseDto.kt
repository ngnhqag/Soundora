package com.soundlab.soundora.data.remote.model

import com.google.gson.annotations.SerializedName

data class TopAlbumResponseDto(
    val data: List<TopAlbumDto?>,
    val total: Int?,
    val next: String?,
    @SerializedName("prev")
    val previous: String? = null
)

