package com.soundlab.soundora.presentation.setting

import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SettingViewModel(
    val dataStoreManager: DataStoreManager
) : BaseMviViewModel<SettingIntent, SettingState, SettingEvent>() {

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

    override fun initState(): SettingState {
        return SettingState()
    }

    override fun processIntent(intent: SettingIntent) {
        when (intent) {
            is SettingIntent.OnBackClick -> {
                sendEvent(SettingEvent.NavigateToHome)
            }
            is SettingIntent.OnOptionClick -> {

            }
            SettingIntent.OnProfileClick -> {
                sendEvent(SettingEvent.NavigateToLibrary)
            }
        }
    }
}