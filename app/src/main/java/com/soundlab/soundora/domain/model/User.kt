package com.soundlab.soundora.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String,
    val name: String,
    val email: String,
    val displayUrl: String
)
