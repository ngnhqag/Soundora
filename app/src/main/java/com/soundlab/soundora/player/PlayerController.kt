package com.soundlab.soundora.player

import com.soundlab.soundora.domain.model.Track


interface PlayerController {
    fun play(track: Track)
    fun resume()
    fun pause()
    fun stop()
    fun connect()
}