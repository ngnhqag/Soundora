package com.soundlab.soundora.util

sealed class AppEvent {
    data class NavigateToLibrary(val tabSelected: Int) : AppEvent()
}