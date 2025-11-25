package com.soundlab.soundora.presentation.login

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.domain.model.User
import com.soundlab.soundora.domain.usecase.SaveUserToFirestoreUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleAuthenticProvider: GoogleAuthenticProvider,
    private val dataStoreManager: DataStoreManager,
    private val saveUserToFirestoreUseCase: SaveUserToFirestoreUseCase
) : BaseMviViewModel<LoginIntent, LoginState, LoginEvent>() {
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
                val firebaseAuth = FirebaseAuth.getInstance()
                val currentUser = firebaseAuth.currentUser
                val user = User(
                    uid = currentUser?.uid ?: "",
                    name = currentUser?.displayName ?: "",
                    email = currentUser?.email ?: "",
                    displayUrl = currentUser?.photoUrl.toString()
                )
                dataStoreManager.saveUserInfo(user)
                saveUserToFirestoreUseCase(user)
                sendEvent(LoginEvent.NavigateToMain)
            } else {
                sendEvent(LoginEvent.LoginError)
            }
            updateState { copy(isLoading = false) }
        }
    }
}