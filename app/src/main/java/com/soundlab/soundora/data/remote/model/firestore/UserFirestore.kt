package com.soundlab.soundora.data.remote.model.firestore

data class UserFirestore(
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String
)