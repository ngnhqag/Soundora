package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.domain.model.Playlist

interface PlaylistDataSource {
    suspend fun savePlaylistToFireStore(playlist: Playlist)
}