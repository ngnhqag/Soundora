package com.soundlab.soundora.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TopAlbum(
    val id: Long,
    val title: String,
    val link: String,
    val cover: String,
    val coverSmall: String,
    val coverMedium: String,
    val coverBig: String,
    val coverXl: String,
    val md5Image: String,
    val recordType: String,
    val trackList: String,
    val explicitLyrics: Boolean,
    val position: Long,
    val artistName: String,
    val type: String
)
