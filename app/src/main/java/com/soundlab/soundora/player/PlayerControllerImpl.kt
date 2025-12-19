package com.soundlab.soundora.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.soundlab.soundora.domain.model.Track

class PlayerControllerImpl(
    context: Context
): PlayerController {

    private val exoPlayer = ExoPlayer.Builder(context).build()

    override fun play(track: Track) {
        val mediaItem = MediaItem.fromUri(track.preview)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun resume() {
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun stop() {
        exoPlayer.stop()
    }
}