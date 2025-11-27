package com.soundlab.soundora.presentation.setting.model

import com.soundlab.soundora.R

enum class LanguageOption(val iconRes: Int, val textRes: Int, val languageCode: String) {
    ENGLISH(R.drawable.ic_united_kingdom_flag, R.string.english, "en"),
    VIETNAMESE(R.drawable.ic_vietnamese_flag, R.string.vietnamese, "vi")
}