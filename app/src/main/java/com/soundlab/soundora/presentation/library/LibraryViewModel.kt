package com.soundlab.soundora.presentation.library

import com.soundlab.soundora.base.BaseMviViewModel

class LibraryViewModel(

) : BaseMviViewModel<LibraryIntent, LibraryState, LibraryEvent>() {
    override fun initState(): LibraryState {
        return LibraryState()
    }

    override fun processIntent(intent: LibraryIntent) {

    }
}