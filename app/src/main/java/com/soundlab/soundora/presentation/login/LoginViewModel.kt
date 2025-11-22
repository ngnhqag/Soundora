package com.soundlab.soundora.presentation.login

import android.app.Activity
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleAuthenticProvider: GoogleAuthenticProvider
) :  BaseMviViewModel<LoginIntent, LoginState, LoginEvent>(){
    override fun initState(): LoginState {
        return LoginState()
    }

    override fun processIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.OnFacebookClick -> {

            }
            is LoginIntent.OnGoogleClick -> {
                handleGoogleClick(intent.context)
            }
            LoginIntent.OnSignUpClick -> {

            }
        }
    }

    private fun handleGoogleClick(context: Context) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val result = googleAuthenticProvider.signIn(context as Activity)
            if (result) {
                sendEvent(LoginEvent.NavigateToMain)
            } else {
                sendEvent(LoginEvent.LoginError)
            }
            updateState { copy(isLoading = false) }
        }
    }
}