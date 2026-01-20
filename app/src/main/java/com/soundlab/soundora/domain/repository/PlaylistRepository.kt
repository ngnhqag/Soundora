package com.soundlab.soundora.domain.repository

import com.soundlab.soundora.domain.model.Playlist

interface PlaylistRepository {
    suspend fun savePlaylistToFirestore(playlist: Playlist): Result<Boolean>
}