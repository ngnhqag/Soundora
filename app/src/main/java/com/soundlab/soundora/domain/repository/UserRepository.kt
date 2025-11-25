package com.soundlab.soundora.domain.repository

import com.soundlab.soundora.domain.model.User

interface UserRepository {
    suspend fun saveUserToFirestore(user: User): Result<Boolean>
}