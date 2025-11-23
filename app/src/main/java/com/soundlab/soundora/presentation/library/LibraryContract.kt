package com.soundlab.soundora.presentation.library

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState

sealed class LibraryIntent : MviIntent {

}

data class LibraryState(
    val isLoading: Boolean = false
) : MviViewState

sealed class LibraryEvent : MviSingleEvent {

}