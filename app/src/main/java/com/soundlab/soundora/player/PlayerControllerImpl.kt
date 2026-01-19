package com.soundlab.soundora.player

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.soundlab.soundora.domain.model.Track
import com.soundlab.soundora.service.PlaybackService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

@UnstableApi
class PlayerControllerImpl(
    private val context: Context
) : PlayerController {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var mediaController: MediaController? = null

    override fun connect() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, PlaybackService::class.java)
        )

        scope.launch {
            mediaController = MediaController.Builder(context, sessionToken)
                .buildAsync()
                .await()
        }
    }

    override fun play(track: Track) {
        val mediaItem = MediaItem.fromUri(track.preview)
        mediaController?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun resume() {
        mediaController?.play()
    }

    override fun pause() {
        mediaController?.pause()
    }

    override fun stop() {
        mediaController?.stop()
    }
}
