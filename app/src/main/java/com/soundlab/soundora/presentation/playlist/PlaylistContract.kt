package com.soundlab.soundora.presentation.playlist

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState

sealed class PlaylistIntent : MviIntent {
    data class OnConfirmCreatePlaylist(val playlistName: String) : PlaylistIntent()
    object OnCreateNewPlaylistClick : PlaylistIntent()
    object OnDismissBottomSheet : PlaylistIntent()
}

data class PlaylistState(
    val isLoading: Boolean = false,
    val showCreatePlaylistBottomSheet: Boolean = false,
) : MviViewState

sealed class PlaylistEvent : MviSingleEvent {

}