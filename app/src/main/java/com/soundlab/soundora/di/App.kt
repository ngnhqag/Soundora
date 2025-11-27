package com.soundlab.soundora.di

import android.app.Application
import android.util.Log
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.util.LanguageHelper
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
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
        applySavedLanguage(koinApp)
    }

    private fun applySavedLanguage(koinApp: KoinApplication) {
        val dataStoreManager: DataStoreManager = koinApp.koin.get()
        runBlocking {
            val storedLanguage = dataStoreManager.getLanguageCode().firstOrNull()
            val languageCode = storedLanguage ?: LanguageHelper.getLanguageCode(this@App)

            LanguageHelper.changeLanguage(this@App, languageCode)

            if (storedLanguage == null) {
                dataStoreManager.saveLanguageCode(languageCode)
            }
            Log.d("App123","$languageCode")
        }

    }
}
