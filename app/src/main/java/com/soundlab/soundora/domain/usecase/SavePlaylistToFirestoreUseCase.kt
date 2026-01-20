package com.soundlab.soundora.domain.usecase

import com.soundlab.soundora.domain.model.Playlist
import com.soundlab.soundora.domain.repository.PlaylistRepository

class SavePlaylistToFirestoreUseCase(
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(playlist: Playlist): Result<Boolean> {
        return playlistRepository.savePlaylistToFirestore(playlist)
    }
}