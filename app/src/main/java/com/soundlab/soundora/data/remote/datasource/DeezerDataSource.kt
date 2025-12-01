package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.model.TopAlbumDto

interface DeezerDataSource {
    suspend fun fetchTopAlbum(): List<TopAlbumDto?>
}