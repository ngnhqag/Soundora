package com.soundlab.soundora.presentation.setting

import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.presentation.setting.model.SettingOption
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.soundlab.soundora.util.LanguageHelper

class SettingViewModel(
    val dataStoreManager: DataStoreManager
) : BaseMviViewModel<SettingIntent, SettingState, SettingEvent>() {

    init {
        getUserFromDataStore()
        getLanguageCodeFromDataStore()
    }

    private fun getLanguageCodeFromDataStore() {
        viewModelScope.launch {
            val languageCode = dataStoreManager.getLanguageCode().firstOrNull()
            languageCode?.let {
                updateState { copy(languageCodeSelected = it) }
            }
        }
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
                handleOnOptionClick(intent.settingOption)
            }

            SettingIntent.OnProfileClick -> {
                sendEvent(SettingEvent.NavigateToLibrary)
            }

            is SettingIntent.OnLanguageBottomSheetStateChange -> {
                updateState { copy(isShowLanguageBottomSheet = intent.isVisible) }
            }

            is SettingIntent.OnSaveLanguage -> {
                handleOnSaveLanguage(intent.languageCode)
            }
        }
    }

    private fun handleOnSaveLanguage(languageCode: String) {
        viewModelScope.launch {
            dataStoreManager.saveLanguageCode(languageCode)
            sendEvent(SettingEvent.ChangeLanguage(languageCode))
            updateState { copy(languageCodeSelected = languageCode) }
        }
    }

    private fun handleOnOptionClick(settingOption: SettingOption) {
        when (settingOption) {
            SettingOption.LANGUAGES -> {
                updateState { copy(isShowLanguageBottomSheet = true) }
            }

            SettingOption.NOTIFICATION -> {

            }

            SettingOption.LOGOUT -> {

            }

            SettingOption.DELETE_ACCOUNT -> {

            }
        }
    }
}