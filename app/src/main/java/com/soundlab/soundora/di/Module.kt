package com.soundlab.soundora.di

import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.data.provider.GoogleAuthenticProviderImpl
import com.soundlab.soundora.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { LoginViewModel(get()) }
    }
}

val authModule by lazy {
    module {
        single<GoogleAuthenticProvider> { GoogleAuthenticProviderImpl() }
    }
}
