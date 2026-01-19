package com.soundlab.soundora.presentation.player

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.player.PlayerController
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val playerController: PlayerController
): BaseMviViewModel<PlayerIntent, PlayerState, PlayerEvent>() {

    init {
        viewModelScope.launch {
            playerController.connect()
        }
    }

    override fun initState(): PlayerState {
        return PlayerState()
    }

    override fun processIntent(intent: PlayerIntent) {
        when (intent) {
            is PlayerIntent.Play -> {
                playerController.play(intent.track)
                updateState {
                    copy(isPlaying = true, currentTrack = intent.track)
                }
            }
            is PlayerIntent.Pause -> {
                playerController.pause()
                updateState {
                    copy(isPlaying = false)
                }
            }
            is PlayerIntent.Resume -> {
                playerController.resume()
                updateState {
                    copy(isPlaying = true)
                }
            }
            is PlayerIntent.Seek -> {

            }
            is PlayerIntent.Next -> {

            }
            is PlayerIntent.Previous -> {

            }
            is PlayerIntent.Stop -> {
                playerController.stop()
                updateState {
                    copy(isPlaying = false)
                }
                sendEvent(PlayerEvent.TrackEnded)
            }
            is PlayerIntent.TogglePlayPause -> {
                if (currentState.isPaused) {
                    playerController.resume()
                    updateState {
                        copy(isPaused = false)
                    }
                }
                else {
                    playerController.pause()
                    updateState {
                        copy(isPaused = true)
                    }
                }
            }
        }
    }

}