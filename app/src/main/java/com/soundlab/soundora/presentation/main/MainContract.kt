package com.soundlab.soundora.presentation.main

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.TopAlbum

sealed class MainIntent : MviIntent {
    data object NavigateToSetting : MainIntent()
    data class NavigateToAlbumView(val topAlbum: TopAlbum) : MainIntent()
    data class OnTabClick(val index: Int) : MainIntent()
}

data class MainState(
    val tabSelected: Int = 0
) : MviViewState

sealed class MainEvent : MviSingleEvent {
    data object NavigateToSetting : MainEvent()
    data class NavigateToAlbumView(val topAlbum: TopAlbum) : MainEvent()
}