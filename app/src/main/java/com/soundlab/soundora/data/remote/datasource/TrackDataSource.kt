package com.soundlab.soundora.data.remote.datasource

import com.soundlab.soundora.data.remote.model.TrackDto

interface TrackDataSource {
    suspend fun getTracksByAlbumId(albumId: Long): List<TrackDto?>
}