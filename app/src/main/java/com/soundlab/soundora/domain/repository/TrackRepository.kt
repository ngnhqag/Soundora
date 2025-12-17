package com.soundlab.soundora.domain.repository

import com.soundlab.soundora.domain.model.Track

interface TrackRepository {
    suspend fun getTracksByAlbumId(albumId: Long): Result<List<Track?>>
}