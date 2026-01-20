package com.soundlab.soundora.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.soundlab.soundora.domain.model.Playlist
import kotlinx.coroutines.tasks.await

    class PlaylistDataSourceImpl(
        private val firestore: FirebaseFirestore,
        private val auth: FirebaseAuth
    ) : PlaylistDataSource {
        override suspend fun savePlaylistToFireStore(playlist: Playlist) {

            val currentUid = auth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val docRef = firestore
                .collection("users")
                .document(currentUid)
                .collection("playlists")
                .document()

            val playlist = playlist.copy(
                id = docRef.id
            )

            docRef
                .set(playlist)
                .await()
        }
    }