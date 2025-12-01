package com.soundlab.soundora.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.domain.usecase.FetchTopAlbumUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchTopAlbumUseCase: FetchTopAlbumUseCase
) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {

    init {
        getAlbumFromDeezer()
    }

    private fun getAlbumFromDeezer() {
        viewModelScope.launch {
            val result = fetchTopAlbumUseCase()
            result.onSuccess { topAlbums ->
                updateState { copy(topAlbums = topAlbums) }
            }
            result.onFailure { it
                Log.d("HomeVM", "$it")
            }
        }
    }

    override fun initState(): HomeState {
        return HomeState()
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnSettingClick -> {
                handleOnSettingClick()
            }

            HomeIntent.OnAlbumClick -> {

            }
        }
    }

    private fun handleOnSettingClick() {
        sendEvent(HomeEvent.NavigateToSetting)
    }
}