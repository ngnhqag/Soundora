package com.soundlab.soundora.presentation.albumview

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.model.Track

sealed class AlbumViewIntent : MviIntent {
    data object OnBackClick : AlbumViewIntent()
    data class LoadTracks(val albumId: Long) : AlbumViewIntent()
    data class OnTrackClick(val track: Track) : AlbumViewIntent()
}

data class AlbumViewState(
    val isLoading: Boolean = false,
    val topAlbums: List<TopAlbum?> = emptyList(),
    val tracks: List<Track?> = emptyList()
) : MviViewState

sealed class AlbumViewEvent: MviSingleEvent {
    data object NavigationToMain: AlbumViewEvent()
    data class PlayTrack(val track: Track): AlbumViewEvent()
}