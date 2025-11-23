package com.soundlab.soundora.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination : NavKey {
    @Serializable
    data object Login : Destination
    @Serializable
    data object Main : Destination
    @Serializable
    data object Splash : Destination
}