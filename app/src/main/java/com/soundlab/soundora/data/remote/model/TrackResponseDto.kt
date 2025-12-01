package com.soundlab.soundora.data.remote.model

data class TrackResponseDto(
    val data: List<TrackDto>,
    val total: Long,
    val next: String,
)
