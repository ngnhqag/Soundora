package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.api.DeezerApiService
import com.soundlab.soundora.data.remote.model.dto.TrackDto

class TrackDataSourceImpl(
    private val api: DeezerApiService
): TrackDataSource {
    override suspend fun getTracksByAlbumId(albumId: Long): List<TrackDto?> {
        val response = api.getAlbumTracks(albumId)
        return response.data
    }
}