package com.soundlab.soundora.presentation.albumview

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.domain.usecase.FetchTopAlbumUseCase
import kotlinx.coroutines.launch

class AlbumViewViewModel(
    private val fetchTopAlbumUseCase: FetchTopAlbumUseCase
): BaseMviViewModel<AlbumViewIntent, AlbumViewState, AlbumViewEvent>() {

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

    override fun initState(): AlbumViewState {
      return AlbumViewState()
    }

    override fun processIntent(intent: AlbumViewIntent) {
        when(intent) {
            AlbumViewIntent.OnBackClick -> {
                sendEvent(AlbumViewEvent.NavigationToMain)
            }
        }
    }

}