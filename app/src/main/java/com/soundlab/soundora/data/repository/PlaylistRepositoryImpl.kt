package com.soundlab.soundora.data.repository

import android.util.Log
import com.soundlab.soundora.data.remote.datasource.PlaylistDataSource
import com.soundlab.soundora.domain.model.Playlist
import com.soundlab.soundora.domain.repository.PlaylistRepository

class PlaylistRepositoryImpl(
    private val playlistDatasource: PlaylistDataSource
) : PlaylistRepository {
    override suspend fun savePlaylistToFirestore(playlist: Playlist): Result<Boolean> {
        return try {
            playlistDatasource.savePlaylistToFireStore(playlist)
            Result.success(true)
        } catch (e: Exception) {
            Log.e("PlaylistRepository", "Save playlist failed", e)
            Result.failure(e)
        }
    }
}