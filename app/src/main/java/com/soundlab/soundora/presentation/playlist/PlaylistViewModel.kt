package com.soundlab.soundora.presentation.playlist

import com.soundlab.soundora.base.BaseMviViewModel

class PlaylistViewModel() : BaseMviViewModel<PlaylistIntent, PlaylistState, PlaylistEvent>() {
    override fun initState(): PlaylistState {
        return PlaylistState()
    }

    override fun processIntent(intent: PlaylistIntent) {

    }

}