package com.soundlab.soundora.data.repository

import com.soundlab.soundora.data.remote.datasource.DeezerDataSource
import com.soundlab.soundora.domain.repository.DeezerRepository
import com.soundlab.soundora.data.mapper.toDomain
import com.soundlab.soundora.domain.model.TopAlbum

class DeezerRepositoryImpl(
    val albumRemoteDataSource: DeezerDataSource
): DeezerRepository {
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