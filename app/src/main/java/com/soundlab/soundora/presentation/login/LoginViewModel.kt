package com.soundlab.soundora.presentation.login

import com.soundlab.soundora.base.BaseMviViewModel

class LoginViewModel :  BaseMviViewModel<LoginIntent, LoginState, LoginEvent>(){
    override fun initState(): LoginState {
        return LoginState()
    }

    override fun processIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.OnFacebookClick -> {

            }
            LoginIntent.OnGoogleClick -> {
                handleGoogleClick()
            }
            LoginIntent.OnSignUpClick -> {

            }
        }
    }

    private fun handleGoogleClick() {

    }
}