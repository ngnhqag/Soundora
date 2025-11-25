package com.soundlab.soundora.presentation.home

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState

sealed class HomeIntent : MviIntent {
    data object OnSettingClick : HomeIntent()
}

data class HomeState(
    val isLoading: Boolean = false
) : MviViewState

sealed class HomeEvent : MviSingleEvent {
    data object NavigateToSetting : HomeEvent()
}