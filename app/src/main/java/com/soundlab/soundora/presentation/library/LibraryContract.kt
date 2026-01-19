package com.soundlab.soundora.presentation.library

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.PlaylistView
import com.soundlab.soundora.domain.model.User

sealed class LibraryIntent : MviIntent {
    object OnSeeAllPlaylistClick : LibraryIntent()
}

data class LibraryState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val playlistCount: Int = 23,
    val followerCount: Int = 58,
    val followingCount: Int = 43,
    val playlistView: List<PlaylistView> = (emptyList())
) : MviViewState

sealed class LibraryEvent : MviSingleEvent {
    object NavigateToPlaylist : LibraryEvent()
}