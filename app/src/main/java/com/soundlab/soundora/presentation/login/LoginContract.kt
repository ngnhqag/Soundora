package com.soundlab.soundora.presentation.login

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState

sealed class LoginIntent : MviIntent {
    data object OnSignUpClick : LoginIntent()
    data object OnGoogleClick : LoginIntent()
    data object OnFacebookClick : LoginIntent()
}

data class LoginState(
    val isLoading: Boolean = false
) : MviViewState

sealed class LoginEvent : MviSingleEvent {
    data object NavigateToMain : LoginEvent()
}
