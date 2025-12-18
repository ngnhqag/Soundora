package com.soundlab.soundora.presentation.player

import com.soundlab.soundora.base.BaseMviViewModel
import com.soundlab.soundora.player.PlayerController

class PlayerViewModel(
    private val playerController: PlayerController
): BaseMviViewModel<PlayerIntent, PlayerState, PlayerEvent>() {
    override fun initState(): PlayerState {
        return PlayerState()
    }

    override fun processIntent(intent: PlayerIntent) {
        when (intent) {
            is PlayerIntent.Play -> {
                playerController.play(intent.url)
                updateState {
                    copy(isPlaying = true, currentTrack = intent.url)
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
        }
    }

}