package com.soundlab.soundora.data.repository

import com.soundlab.soundora.data.mapper.toDomain
import com.soundlab.soundora.data.remote.datasource.AlbumDataSource
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.model.Track
import com.soundlab.soundora.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    val albumRemoteDataSource: AlbumDataSource
): AlbumRepository {
    override suspend fun fetchTopAlbum(): Result<List<TopAlbum?>> {
        return try {
            val topAlbumsDto  = albumRemoteDataSource.fetchTopAlbum()
            val topAlbums = topAlbumsDto.map { it?.toDomain() }
            Result.success(topAlbums)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}