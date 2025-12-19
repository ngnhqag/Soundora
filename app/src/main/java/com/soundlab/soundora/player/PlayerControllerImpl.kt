package com.soundlab.soundora.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import com.soundlab.soundora.domain.model.Track

@UnstableApi
class PlayerControllerImpl(
    context: Context
): PlayerController {


    private val loadControl = DefaultLoadControl.Builder()
        .setBufferDurationsMs(
            15_000,
            30_000,
            5_000,
            10_000
        )
        .build()
    private val exoPlayer = ExoPlayer.Builder(context)
        .setLoadControl(loadControl)
        .build()

    override fun play(track: Track) {
        val mediaItem = MediaItem.fromUri(track.preview)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
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