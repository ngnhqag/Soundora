package com.soundlab.soundora.presentation.login.model

import com.soundlab.soundora.R

enum class LoginScreenButton(val iconRes: Int, val text: Int) {
    GOOGLE(R.drawable.ic_google, R.string.continue_with_google),
    FACEBOOK(R.drawable.ic_facebook, R.string.continue_with_facebook)
}