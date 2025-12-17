package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.api.DeezerApiService
import com.soundlab.soundora.data.remote.model.TopAlbumDto

class AlbumDataSourceImpl(
    private val api: DeezerApiService
) : AlbumDataSource {
    override suspend fun fetchTopAlbum(): List<TopAlbumDto?> {
        val response = api.getTopAlbums()
        return response.data
    }
}