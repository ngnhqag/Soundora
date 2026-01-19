package com.soundlab.soundora.presentation.setting

import android.app.Activity
import android.app.Application
import android.app.LocaleManager
import android.content.Intent
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.data.local.datastore.DataStoreManager
import com.soundlab.soundora.data.provider.GoogleAuthenticProvider
import com.soundlab.soundora.presentation.setting.model.SettingOption
import com.soundlab.soundora.util.LanguageHelper
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SettingViewModel(
    private val dataStoreManager: DataStoreManager,
    private val context: Application,
    private val googleAuthenticProvider: GoogleAuthenticProvider
) : BaseMviViewModel<SettingIntent, SettingState, SettingEvent>() {

    init {
        getUserFromDataStore()
        getLanguageCodeFromDataStore()
    }

    private fun getLanguageCodeFromDataStore() {
        viewModelScope.launch {
            val languageCode = dataStoreManager.getLanguageCode().firstOrNull()
            if (languageCode != null) {
                updateState { copy(languageCodeSelected = languageCode) }
            } else {
                val currentLanguageCode = LanguageHelper.getLanguageCode(context)
                updateState { copy(languageCodeSelected = currentLanguageCode) }
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }

        val intent = context.packageManager
            .getLaunchIntentForPackage(context.packageName)
            ?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        context.startActivity(intent)
        if (context is Activity) {
            context.finish()
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
//                googleAuthenticProvider.signOut()
            }

            SettingOption.DELETE_ACCOUNT -> {

            }
        }
    }
}