package com.soundlab.soundora.data.remote.model.dto

data class TrackResponseDto(
    val data: List<TrackDto>,
    val total: Long,
    val next: String,
)
