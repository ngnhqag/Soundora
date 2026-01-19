package com.soundlab.soundora.presentation.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@Composable
fun PlaylistScreen() {
    PlaylistContent()
}

@Composable
fun PlaylistContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .padding(top = 28.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.img_avatar)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 12.dp)
            )

            Text(
                text = stringResource(R.string.your_library),
                style = SoundoraTypography.Title.Large.SemiBold,
                color = SoundoraColors.Text.TextPrimary,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .height(32.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .clickable(
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add2),
                contentDescription = null,
                tint = SoundoraColors.White,
                modifier = Modifier
                    .size(67.dp)
                    .background(
                        color = SoundoraColors.BackGround.BackgroundSecondary,
                        shape = CircleShape
                    )
                    .padding(16.dp)
            )

            Text(
                text = "Create New Playlist",
                style = SoundoraTypography.Title.Medium.Medium,
                color = SoundoraColors.Text.TextPrimary,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PlaylistPreview() {
    PlaylistContent()
}