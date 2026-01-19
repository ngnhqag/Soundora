package com.soundlab.soundora.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.data.local.datastore.DataStoreManagerImpl
import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.data.provider.GoogleAuthenticProviderImpl
import com.soundlab.soundora.data.remote.api.DeezerApiClient
import com.soundlab.soundora.data.remote.api.DeezerApiService
import com.soundlab.soundora.data.remote.datasource.AlbumDataSource
import com.soundlab.soundora.data.remote.datasource.AlbumDataSourceImpl
import com.soundlab.soundora.data.remote.datasource.TrackDataSource
import com.soundlab.soundora.data.remote.datasource.TrackDataSourceImpl
import com.soundlab.soundora.data.remote.datasource.UserRemoteDataSource
import com.soundlab.soundora.data.remote.datasource.UserRemoteDataSourceImpl
import com.soundlab.soundora.data.repository.AlbumRepositoryImpl
import com.soundlab.soundora.data.repository.TrackRepositoryImpl
import com.soundlab.soundora.data.repository.UserRepositoryImpl
import com.soundlab.soundora.domain.repository.AlbumRepository
import com.soundlab.soundora.domain.repository.TrackRepository
import com.soundlab.soundora.domain.repository.UserRepository
import com.soundlab.soundora.domain.usecase.FetchTopAlbumUseCase
import com.soundlab.soundora.domain.usecase.GetTracksByAlbumIdUseCase
import com.soundlab.soundora.domain.usecase.SaveUserToFirestoreUseCase
import com.soundlab.soundora.player.PlayerController
import com.soundlab.soundora.player.PlayerControllerImpl
import com.soundlab.soundora.presentation.albumview.AlbumViewViewModel
import com.soundlab.soundora.presentation.home.HomeViewModel
import com.soundlab.soundora.presentation.library.LibraryViewModel
import com.soundlab.soundora.presentation.login.LoginViewModel
import com.soundlab.soundora.presentation.main.MainViewModel
import com.soundlab.soundora.presentation.player.PlayerViewModel
import com.soundlab.soundora.presentation.search.SearchViewModel
import com.soundlab.soundora.presentation.setting.SettingViewModel
import com.soundlab.soundora.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule by lazy {
    module {
        viewModel { LoginViewModel(get(), get(), get()) }
        viewModel { MainViewModel() }
        viewModel { HomeViewModel(get()) }
        viewModel { SearchViewModel() }
        viewModel { LibraryViewModel(get()) }
        viewModel { SplashViewModel() }
        viewModel { SettingViewModel(get(), get(), get()) }
        viewModel { AlbumViewViewModel(get(), get()) }
        viewModel { PlayerViewModel(get()) }
    }
}

val firebaseModule by lazy {
    module {
        single<FirebaseFirestore> {
            val firestore = FirebaseFirestore.getInstance()

            val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build()

            firestore.firestoreSettings = settings

            firestore
        }
        single<GoogleAuthenticProvider> { GoogleAuthenticProviderImpl() }
    }
}

val localDataModule by lazy {
    module {
        single<DataStore<Preferences>> {
            PreferenceDataStoreFactory.create(
                produceFile = { get<Context>().preferencesDataStoreFile("soundora_preferences") })
        }
        single<DataStoreManager> { DataStoreManagerImpl(get()) }
    }
}

val remoteDataModule by lazy {
    module {
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
        single<AlbumDataSource> { AlbumDataSourceImpl(get()) }
        single<TrackDataSource> { TrackDataSourceImpl(get()) }
    }
}

val repositoryModule by lazy {
    module {
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<AlbumRepository> { AlbumRepositoryImpl(get()) }
        single<TrackRepository> { TrackRepositoryImpl(get()) }
    }
}

val useCaseModule by lazy {
    module {
        factory { SaveUserToFirestoreUseCase(get()) }
        factory { FetchTopAlbumUseCase(get()) }
        factory { GetTracksByAlbumIdUseCase(get()) }
    }
}

val deezerModule by lazy {
    module {
        single<DeezerApiService> { DeezerApiClient.build() }
    }
}

@OptIn(androidx.media3.common.util.UnstableApi::class)
val playerModule by lazy {
    module {
        single<PlayerController> { PlayerControllerImpl(context = androidContext()) }
    }
}