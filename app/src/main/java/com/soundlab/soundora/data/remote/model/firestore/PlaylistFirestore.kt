package com.soundlab.soundora.data.remote.model.firestore

data class PlaylistFirestore(
    val id: String = "",
    val name: String = "",
    val songCount: Int = 0,
    val trackIds: List<String> = emptyList()
)
