package com.soundlab.soundora.presentation.search

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.User

sealed class SearchIntent : MviIntent {

}

data class SearchState(
    val isLoading: Boolean = false,
) : MviViewState

sealed class SearchEvent : MviSingleEvent {

}