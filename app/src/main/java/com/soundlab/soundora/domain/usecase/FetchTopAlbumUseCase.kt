package com.soundlab.soundora.domain.usecase

import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.repository.AlbumRepository

class FetchTopAlbumUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(): Result<List<TopAlbum?>> {
        return albumRepository.fetchTopAlbum()
    }
}