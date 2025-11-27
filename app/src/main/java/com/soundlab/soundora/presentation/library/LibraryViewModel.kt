package com.soundlab.soundora.presentation.library

import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LibraryViewModel(
    val dataStoreManager: DataStoreManager
) : BaseMviViewModel<LibraryIntent, LibraryState, LibraryEvent>() {
    init {
        getUserFromDataStore()
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