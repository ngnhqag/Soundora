package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.model.firestore.UserFirestore

interface UserRemoteDataSource {
   suspend fun saveUserToFireStore(user: UserFirestore)
}