package com.soundlab.soundora.presentation.albumview

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.TopAlbum

sealed class AlbumViewIntent : MviIntent {
    data object OnBackClick : AlbumViewIntent()
}

data class AlbumViewState(
    val isLoading: Boolean = false,
    val topAlbums: List<TopAlbum?> = emptyList()
) : MviViewState

sealed class AlbumViewEvent: MviSingleEvent {
    data object NavigationToMain: AlbumViewEvent()
}