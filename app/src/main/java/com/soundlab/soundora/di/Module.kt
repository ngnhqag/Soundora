package com.soundlab.soundora.di

import com.soundlab.soundora.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { LoginViewModel() }
    }
}
