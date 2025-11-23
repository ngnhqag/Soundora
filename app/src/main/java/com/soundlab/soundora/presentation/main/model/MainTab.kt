package com.soundlab.soundora.presentation.main.model

import com.soundlab.soundora.R

enum class MainTab(val textRes: Int, val iconRes: Int, val index: Int) {
    HOME(textRes = R.string.home, iconRes = R.drawable.ic_home, 0),
    SEARCH(textRes = R.string.search, iconRes = R.drawable.ic_search, 1),
    LIBRARY(textRes = R.string.your_library, iconRes = R.drawable.ic_library, 2)
}