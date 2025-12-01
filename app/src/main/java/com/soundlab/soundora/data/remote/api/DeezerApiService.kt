package com.soundlab.soundora.data.remote.api

import com.soundlab.soundora.data.remote.model.AlbumDto
import com.soundlab.soundora.data.remote.model.ArtistDto
import com.soundlab.soundora.data.remote.model.TopAlbumResponseDto
import com.soundlab.soundora.data.remote.model.TrackResponseDto
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
}
