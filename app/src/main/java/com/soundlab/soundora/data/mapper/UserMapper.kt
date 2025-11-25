package com.soundlab.soundora.data.mapper

import com.soundlab.soundora.data.remote.model.UserFirestore
import com.soundlab.soundora.domain.model.User

fun User.toFirestore(): UserFirestore {
    return UserFirestore(
        uid = this.uid,
        displayName = this.name,
        email = this.email,
        photoUrl = this.displayUrl
    )
}

fun UserFirestore.toDomain(): User {
    return User(
        uid = this.uid,
        name = this.displayName,
        email = this.email,
        displayUrl = this.photoUrl
    )
}