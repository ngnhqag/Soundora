package com.soundlab.soundora.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val koinApp = startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                firebaseModule,
                localDataModule,
                remoteDataModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}
