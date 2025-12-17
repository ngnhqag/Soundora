package com.soundlab.soundora.domain.repository

import com.soundlab.soundora.domain.model.TopAlbum


interface AlbumRepository {
    suspend fun fetchTopAlbum(): Result<List<TopAlbum?>>
}