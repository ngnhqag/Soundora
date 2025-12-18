package com.soundlab.soundora.player


interface PlayerController {
    fun play(url: String)
    fun resume()
    fun pause()
    fun stop()
}