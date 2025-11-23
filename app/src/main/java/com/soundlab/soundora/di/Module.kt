package com.soundlab.soundora.di

import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.data.provider.GoogleAuthenticProviderImpl
import com.soundlab.soundora.presentation.home.HomeViewModel
import com.soundlab.soundora.presentation.library.LibraryViewModel
import com.soundlab.soundora.presentation.login.LoginViewModel
import com.soundlab.soundora.presentation.main.MainViewModel
import com.soundlab.soundora.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel() }
        viewModel { HomeViewModel() }
        viewModel { SearchViewModel() }
        viewModel { LibraryViewModel() }
    }
}

val authModule by lazy {
    module {
        single<GoogleAuthenticProvider> { GoogleAuthenticProviderImpl() }
    }
}
