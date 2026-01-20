package com.soundlab.soundora.data.remote.api

import com.soundlab.soundora.data.remote.model.dto.AlbumDto
import com.soundlab.soundora.data.remote.model.dto.ArtistDto
import com.soundlab.soundora.data.remote.model.dto.TopAlbumResponseDto
import com.soundlab.soundora.data.remote.model.dto.TrackResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApiService {

    @GET("search")
    suspend fun searchTrack(
        @Query("q") query: String
    ): TrackResponseDto

    @GET("album/{id}")
    suspend fun getAlbum(
        @Path("id") albumId: Long
    ): AlbumDto

    @GET("artist/{id}")
    suspend fun getArtist(
        @Path("id") artistId: Long
    ): ArtistDto

    @GET("chart/0/albums")
    suspend fun getTopAlbums(): TopAlbumResponseDto

    @GET("album/{id}/tracks")
    suspend fun getAlbumTracks(
        @Path("id") albumId: Long
    ): TrackResponseDto

    @GET("artist/{id}/top")
    suspend fun getArtistTopTracks(
        @Path("id") artistId: Long,
        @Query("limit") limit: Int = 10
    ): TrackResponseDto
}
