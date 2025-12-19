package com.soundlab.soundora.presentation.player

import com.soundlab.soundora.base.MviIntent
import com.soundlab.soundora.base.MviSingleEvent
import com.soundlab.soundora.base.MviViewState
import com.soundlab.soundora.domain.model.Track

sealed class PlayerIntent: MviIntent {
    data class Play(val track: Track): PlayerIntent()
    data object Pause: PlayerIntent()
    data object Resume: PlayerIntent()
    data class Seek(val position: Long) : PlayerIntent()
    data object Next : PlayerIntent()
    data object Previous : PlayerIntent()
    data object Stop : PlayerIntent()
    data object TogglePlayPause: PlayerIntent()
}

data class PlayerState(
    val isPlaying: Boolean = false,
    val isBuffering: Boolean = false,
    val currentTrack: Track? = null,
    val position: Long = 0L,
    val duration: Long = 0L,
    val queue: List<Track> = emptyList(),
    val isPaused: Boolean = false,
) : MviViewState

sealed class PlayerEvent: MviSingleEvent {
    data class Error(val message: String) : PlayerEvent()
    object TrackEnded : PlayerEvent()
}