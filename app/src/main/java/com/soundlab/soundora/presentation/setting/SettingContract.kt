package com.soundlab.soundora.presentation.setting

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.User
import com.soundlab.soundora.presentation.setting.model.SettingOption

sealed class SettingIntent : MviIntent {
    data object OnBackClick: SettingIntent()
    data object OnProfileClick: SettingIntent()
    data class OnOptionClick(val settingOption: SettingOption): SettingIntent()
}

data class SettingState(
    val isLoading: Boolean = false,
    val user: User? = null
) : MviViewState

sealed class SettingEvent : MviSingleEvent {
    data object NavigateToHome: SettingEvent()
    data object NavigateToLibrary: SettingEvent()
}