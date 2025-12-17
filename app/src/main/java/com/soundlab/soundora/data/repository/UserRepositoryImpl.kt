package com.soundlab.soundora.data.repository

import com.soundlab.soundora.data.mapper.toFirestore
import com.soundlab.soundora.data.remote.datasource.UserRemoteDataSource
import com.soundlab.soundora.domain.model.User
import com.soundlab.soundora.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun saveUserToFirestore(user: User): Result<Boolean> {
        return try {
            remoteDataSource.saveUserToFireStore(user.toFirestore())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}