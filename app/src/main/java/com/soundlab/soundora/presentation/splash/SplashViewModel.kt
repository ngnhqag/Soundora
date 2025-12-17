package com.soundlab.soundora.presentation.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.soundlab.soundora.base.BaseMviViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(

) : BaseMviViewModel<SplashIntent, SplashState, SplashEvent>() {
    init {
        checkLogin()
    }

    private fun checkLogin() {
        viewModelScope.launch {
            val firebase = FirebaseAuth.getInstance()
            val user = firebase.currentUser
            delay(1500)
            if (user != null) {
                sendEvent(SplashEvent.NavigateToMain)
            } else {
                sendEvent(SplashEvent.NavigateToLogin)
            }
        }
    }

    override fun initState(): SplashState {
        return SplashState()
    }

    override fun processIntent(intent: SplashIntent) {

    }
}