package com.soundlab.soundora.presentation.playlist

import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.domain.model.Playlist
import com.soundlab.soundora.domain.usecase.SavePlaylistToFirestoreUseCase
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val savePlaylistToFirestoreUseCase: SavePlaylistToFirestoreUseCase
) : BaseMviViewModel<PlaylistIntent, PlaylistState, PlaylistEvent>() {
    override fun initState(): PlaylistState {
        return PlaylistState()
    }

    override fun processIntent(intent: PlaylistIntent) {
        when (intent) {
            is PlaylistIntent.OnConfirmCreatePlaylist -> {
                updateState { copy(showCreatePlaylistBottomSheet = false) }
                val playlist = Playlist(
                    name = intent.playlistName
                )
                viewModelScope.launch {
                    savePlaylistToFirestoreUseCase(playlist)
                }
            }
            is PlaylistIntent.OnDismissBottomSheet -> {
                updateState { copy(showCreatePlaylistBottomSheet = false) }
            }
            is PlaylistIntent.OnCreateNewPlaylistClick -> {
                updateState { copy(showCreatePlaylistBottomSheet = true) }
            }
        }
    }

}