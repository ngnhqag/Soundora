package com.soundlab.soundora.presentation.main

import com.soundlab.soundora.base.BaseMviViewModel

class MainViewModel(

) : BaseMviViewModel<MainIntent, MainState, MainEvent>() {
    override fun initState(): MainState {
        return MainState()
    }

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnTabClick -> {
                handleOnTabClick(intent.index)
            }
            is MainIntent.NavigateToSetting -> {
                sendEvent(MainEvent.NavigateToSetting)
            }
        }
    }

    private fun handleOnTabClick(index: Int) {
        updateState { copy(tabSelected = index) }
    }
}