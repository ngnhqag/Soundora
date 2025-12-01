package com.soundlab.soundora.domain.repository

import com.soundlab.soundora.domain.model.TopAlbum


interface DeezerRepository {
    suspend fun fetchTopAlbum(): Result<List<TopAlbum?>>
}