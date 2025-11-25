package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.model.UserFirestore

interface UserRemoteDataSource {
   suspend fun saveUserToFireStore(user: UserFirestore)
}