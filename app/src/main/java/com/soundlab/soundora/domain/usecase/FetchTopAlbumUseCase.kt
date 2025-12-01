package com.soundlab.soundora.domain.usecase

import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.repository.DeezerRepository

class FetchTopAlbumUseCase(
    private val deezerRepository: DeezerRepository
) {
    suspend operator fun invoke(): Result<List<TopAlbum?>> {
        return deezerRepository.fetchTopAlbum()
    }
}