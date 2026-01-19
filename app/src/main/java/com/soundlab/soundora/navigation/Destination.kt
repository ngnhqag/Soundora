package com.soundlab.soundora.navigation

import androidx.navigation3.runtime.NavKey
import com.soundlab.soundora.domain.model.TopAlbum
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination : NavKey {
    @Serializable
    data object Login : Destination
    @Serializable
    data object Main : Destination
    @Serializable
    data object Splash : Destination
    @Serializable
    data object Setting : Destination
    @Serializable
    data class AlbumView(val topAlbum: TopAlbum) : Destination
    @Serializable
    data object Playlist : Destination
    @Serializable
    data object Library : Destination

}