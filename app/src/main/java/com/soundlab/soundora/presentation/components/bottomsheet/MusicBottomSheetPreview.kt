package com.soundlab.soundora.presentation.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraShapes

@Composable
fun MusicBottomSheet(
    modifier: Modifier
) {
    MusicBottomSheetContent()
}

@Composable
fun MusicBottomSheetContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.img_album),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(SoundoraShapes.extraSmall),
            )

            Text(
                text = stringResource(R.string.music).take(35),
                color = SoundoraColors.Text.TextPrimary,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_bluetooth),
                contentDescription = null,
                tint = SoundoraColors.Primary.Primary,
                modifier = Modifier
                    .size(24.dp)
            )

            IconButton(
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pause),
                    contentDescription = null,
                    tint = SoundoraColors.White,
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
   MusicBottomSheetContent()
}