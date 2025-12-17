package com.soundlab.soundora.domain.usecase

import com.soundlab.soundora.domain.model.Track
import com.soundlab.soundora.domain.repository.TrackRepository

class GetTracksByAlbumIdUseCase(
    private val trackRepository: TrackRepository
) {
    suspend operator fun invoke(albumId: Long): Result<List<Track?>> {
        return trackRepository.getTracksByAlbumId(albumId)
    }
}