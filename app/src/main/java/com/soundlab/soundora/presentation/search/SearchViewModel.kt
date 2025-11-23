package com.soundlab.soundora.presentation.search

import com.soundlab.soundora.base.BaseMviViewModel

class SearchViewModel(

) : BaseMviViewModel<SearchIntent, SearchState, SearchEvent>() {
    override fun initState(): SearchState {
        return SearchState()
    }

    override fun processIntent(intent: SearchIntent) {

    }
}