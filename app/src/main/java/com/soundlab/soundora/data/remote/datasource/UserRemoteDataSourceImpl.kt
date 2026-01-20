package com.soundlab.soundora.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.soundlab.soundora.data.remote.model.firestore.UserFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
) : UserRemoteDataSource {
    override suspend fun saveUserToFireStore(user: UserFirestore) {
        firestore
            .collection("users")
            .document(user.uid)
            .set(user)
            .await()
    }
}