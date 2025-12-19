package com.soundlab.soundora.presentation.components.bottomsheet

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.soundlab.soundora.R
import com.soundlab.soundora.domain.model.Track
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.util.ext.getDeezerCoverUrl

@Composable
fun MusicBottomSheet(
    modifier: Modifier = Modifier,
    track: Track,
    isPaused: Boolean,
    onStopClick: () -> Unit,
    onPlayPauseClick: () -> Unit
) {
        MusicBottomSheetContent(
            modifier, track, isPaused,
            onStopClick = {
                onStopClick()
            },
            onPlayPauseClick = {
                onPlayPauseClick()
            }
    )
}

@Composable
fun MusicBottomSheetContent(
    modifier: Modifier = Modifier,
    track: Track,
    isPaused: Boolean,
    onStopClick: () -> Unit,
    onPlayPauseClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(track.md5Image.getDeezerCoverUrl(40)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(SoundoraShapes.extraSmall),
            )

            Text(
                text = track.title.take(35),
                color = SoundoraColors.Text.TextPrimary,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

           IconButton(
               onClick = {
                   onStopClick()
               }
           ) {
               Icon(
                   painter = painterResource(R.drawable.ic_stop),
                   contentDescription = null,
                   tint = SoundoraColors.Primary.Primary,
                   modifier = Modifier
                       .size(24.dp)
               )
           }

            IconButton(
                onClick = {
                    onPlayPauseClick()
                }
            ) {
                val iconPlayPause = if (isPaused) R.drawable.ic_play else R.drawable.ic_pause
                Log.d("MusicBottomSheet", "isPaused: $isPaused")
                Icon(
                    painter = painterResource(iconPlayPause),
                    contentDescription = null,
                    tint = SoundoraColors.Primary.Primary,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MusicBottomSheetPreview() {
   MusicBottomSheetContent(
       modifier = Modifier,
       track = Track(),
       isPaused = true,
       onStopClick = { },
       onPlayPauseClick = { }
   )
}