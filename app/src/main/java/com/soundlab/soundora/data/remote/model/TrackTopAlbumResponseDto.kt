package com.soundlab.soundora.data.remote.model

data class TrackTopAlbumResponseDto(
    val data: List<TopAlbumDto?>,
    val total: Int?,
    val next: String?,
)
