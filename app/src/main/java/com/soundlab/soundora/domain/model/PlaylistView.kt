package com.soundlab.soundora.domain.model

data class PlaylistView(
    val id: String,
    val name: String,
    val createAt: Long,
    val thumbnail: String,
    val songCount: Int
)