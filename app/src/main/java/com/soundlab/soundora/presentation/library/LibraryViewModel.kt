package com.soundlab.soundora.presentation.library

import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.domain.model.PlaylistView
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.collections.listOf

class LibraryViewModel(
    val dataStoreManager: DataStoreManager
) : BaseMviViewModel<LibraryIntent, LibraryState, LibraryEvent>() {
    init {
        getUserFromDataStore()
        templatePlaylistView()
    }

    private fun templatePlaylistView() {
        val playlistView = listOf(
            PlaylistView(id = "1", name = "Shazam", createAt = 1, thumbnail = "https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/avatar_vo_tri_a49436c5de.jpg", songCount = 4),
            PlaylistView(id = "2", name = "Roadtrip", createAt = 1, thumbnail = "https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/avatar_vo_tri_a49436c5de.jpg", songCount = 4),
            PlaylistView(id = "3", name = "Study", createAt = 1, thumbnail = "https://cdn2.fptshop.com.vn/unsafe/1920x0/filters:format(webp):quality(75)/avatar_vo_tri_a49436c5de.jpg", songCount = 4)
        )

        updateState { copy( playlistView = playlistView) }
    }

    private fun getUserFromDataStore() {
        viewModelScope.launch {
            val user = dataStoreManager.getUserInfo().firstOrNull()
            user?.let {
                updateState {
                    copy(user = it)
                }
            }
        }
    }

    override fun initState(): LibraryState {
        return LibraryState()
    }

    override fun processIntent(intent: LibraryIntent) {

    }
}