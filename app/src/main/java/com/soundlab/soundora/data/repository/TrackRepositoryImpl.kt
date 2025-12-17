package com.soundlab.soundora.data.repository

import com.soundlab.soundora.data.mapper.toDomain
import com.soundlab.soundora.data.remote.datasource.TrackDataSource
import com.soundlab.soundora.domain.model.Track
import com.soundlab.soundora.domain.repository.TrackRepository

class TrackRepositoryImpl(
    private val trackRemoteDataSource: TrackDataSource
): TrackRepository {
    override suspend fun getTracksByAlbumId(albumId: Long): Result<List<Track?>> {
        return try {
            val tracksDto = trackRemoteDataSource.getTracksByAlbumId(albumId)
            val tracks = tracksDto.map { it?.toDomain() }
            Result.success(tracks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}