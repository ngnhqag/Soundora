package com.soundlab.soundora.presentation.home

import com.soundlab.soundora.base.BaseMviViewModel

class HomeViewModel(

) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {
    override fun initState(): HomeState {
        return HomeState()
    }

    override fun processIntent(intent: HomeIntent) {

    }
}