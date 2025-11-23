package com.soundlab.soundora.di

import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.data.provider.GoogleAuthenticProviderImpl
import com.soundlab.soundora.presentation.login.LoginViewModel
import com.soundlab.soundora.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel() }
    }
}

val authModule by lazy {
    module {
        single<GoogleAuthenticProvider> { GoogleAuthenticProviderImpl() }
    }
}
