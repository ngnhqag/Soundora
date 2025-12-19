package com.soundlab.soundora.di

import android.app.Application
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class App : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                firebaseModule,
                localDataModule,
                remoteDataModule,
                repositoryModule,
                useCaseModule,
                deezerModule,
                playerModule
            )
        }
        loadLanguageFromDataStore()
    }
    
    private fun loadLanguageFromDataStore() {
        applicationScope.launch {
            try {
                val dataStoreManager: DataStoreManager = GlobalContext.get().get()
                val languageCode = dataStoreManager.getLanguageCode().firstOrNull()
                languageCode?.let {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(it)
                    } else {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(it))
                    }
                }
            } catch (e: Exception) {
            }
        }
    }
}
