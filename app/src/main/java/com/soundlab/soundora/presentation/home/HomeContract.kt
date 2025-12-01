package com.soundlab.soundora.presentation.home

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.TopAlbum

sealed class HomeIntent : MviIntent {
    data object OnSettingClick : HomeIntent()
    data object OnAlbumClick : HomeIntent()
}

data class HomeState(
    val isLoading: Boolean = false,
    val topAlbums: List<TopAlbum?> = emptyList()
) : MviViewState

sealed class HomeEvent : MviSingleEvent {
    data object NavigateToSetting : HomeEvent()
}