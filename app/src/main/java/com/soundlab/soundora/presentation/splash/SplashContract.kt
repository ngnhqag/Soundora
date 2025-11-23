package com.soundlab.soundora.presentation.splash

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState

sealed class SplashIntent : MviIntent {

}

data class SplashState(
    val isLoading: Boolean = false
) : MviViewState

sealed class SplashEvent : MviSingleEvent {
    data object NavigateToMain : SplashEvent()
    data object NavigateToLogin : SplashEvent()
}