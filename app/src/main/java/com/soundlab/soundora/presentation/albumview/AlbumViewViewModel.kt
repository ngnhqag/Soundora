package com.soundlab.soundora.presentation.albumview

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.usecase.FetchTopAlbumUseCase
import com.soundlab.soundora.domain.usecase.GetTracksByAlbumIdUseCase
import kotlinx.coroutines.launch

class AlbumViewViewModel(
    private val fetchTopAlbumUseCase: FetchTopAlbumUseCase,
    private val getTracksByAlbumIdUseCase: GetTracksByAlbumIdUseCase
): BaseMviViewModel<AlbumViewIntent, AlbumViewState, AlbumViewEvent>() {

    init {
        getTopAlbumFromDeezer()
    }

    private fun getTopAlbumFromDeezer() {
        viewModelScope.launch {
            val result = fetchTopAlbumUseCase()
            result.onSuccess { topAlbums ->
                updateState { copy(topAlbums = topAlbums) }
            }
            result.onFailure { it
                Log.d("AlbumViewVM", "$it")
            }
        }
    }

    private fun loadTracks(albumId: Long) {
        viewModelScope.launch {
            val result = getTracksByAlbumIdUseCase(albumId)
            result.onSuccess { tracks ->
                updateState { copy(tracks = tracks) }
            }
            result.onFailure {
                Log.d("AlbumViewVMLoadTrack", "$it")
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

            is AlbumViewIntent.LoadTracks -> {
                loadTracks(intent.albumId)
            }
        }
    }

}