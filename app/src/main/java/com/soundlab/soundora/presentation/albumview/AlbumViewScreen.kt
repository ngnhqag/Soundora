package com.soundlab.soundora.presentation.albumview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.soundlab.soundora.R
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.presentation.components.view.VerticalGradientBox
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import com.soundlab.soundora.util.ext.getDominantColor
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.lazy.items
import com.soundlab.soundora.presentation.player.PlayerIntent
import com.soundlab.soundora.presentation.player.PlayerViewModel

@Composable
fun AlbumViewScreen(
    topAlbum: TopAlbum,
    viewModel: AlbumViewViewModel = koinViewModel(),
    playerViewModel: PlayerViewModel = koinViewModel(),
    navigateToMain: () -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    var dominantColor by remember { mutableStateOf(SoundoraColors.BackGround.BackgroundPrimary) }

    LaunchedEffect(topAlbum) {
        dominantColor = topAlbum.coverMedium.getDominantColor(
            context,
            SoundoraColors.BackGround.BackgroundPrimary
        )
        viewModel.processIntent(AlbumViewIntent.LoadTracks(topAlbum.id))
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { events ->
            when (events) {
                AlbumViewEvent.NavigationToMain -> {
                    navigateToMain()
                }

                is AlbumViewEvent.PlayTrack -> {
                    playerViewModel.processIntent(PlayerIntent.Play(events.url))
                }
            }

        }
    }

    AlbumViewScreenContent(
        dominantColor = dominantColor,
        state = state.value,
        topAlbum = topAlbum,
        onBackClick = {
            viewModel.processIntent(AlbumViewIntent.OnBackClick)
        },
        onTrackClick = {
            viewModel.processIntent(AlbumViewIntent.OnTrackClick(it))
        }
    )
    Log.d("AlbumViewScreen", "$topAlbum")
    Log.d("AlbumViewScreen", "$dominantColor")
}

@Composable
fun AlbumViewScreenContent(
    dominantColor: Color,
    state: AlbumViewState,
    topAlbum: TopAlbum,
    onBackClick: () -> Unit,
    onTrackClick: (url: String) -> Unit

) {
    Log.d("AlbumViewScreen123", "$state")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoundoraColors.BackGround.BackgroundPrimary)
    ) {
        VerticalGradientBox(
            colorTop = dominantColor,
            colorBottom = SoundoraColors.BackGround.BackgroundPrimary,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding((WindowInsets.statusBars.asPaddingValues()))
        ) {
            IconButton(
                onClick = {
                    onBackClick()
                },
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .size(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = SoundoraColors.Neutral.Neutral00,
                    modifier = Modifier,
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(topAlbum.coverMedium)
                    .crossfade(true)
                    .placeholder(R.drawable.img_album)
                    .error(R.drawable.img_album)
                    .fallback(R.drawable.img_album)
                    .size(250).build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(236.dp)
                    .align(Alignment.CenterHorizontally),
                )

            Text(
                text = topAlbum.title,
                color = SoundoraColors.Text.TextPrimary,
                style = SoundoraTypography.Headline.Small.SemiBold,
                modifier = Modifier
                    .padding(top = 40.dp, start = 12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(topAlbum.coverSmall)
                                .crossfade(true)
                                .placeholder(R.drawable.img_artist)
                                .error(R.drawable.img_artist)
                                .fallback(R.drawable.img_artist)
                                .size(50).build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                        )

                        Text(
                            text = topAlbum.artistName,
                            style = SoundoraTypography.Title.Medium.SemiBold,
                            color = SoundoraColors.Text.TextPrimary,
                            modifier = Modifier
                                .padding(start = 12.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.album_2000),
                            style = SoundoraTypography.Body.Small.SemiBold,
                            color = SoundoraColors.Neutral.Neutral01,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_like),
                            tint = SoundoraColors.Neutral.Neutral01,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )

                        Icon(
                            painter = painterResource(R.drawable.ic_download),
                            contentDescription = null,
                            tint = SoundoraColors.Primary.Primary,
                            modifier = Modifier
                                .padding(start = 32.dp)
                                .size(20.dp)
                        )

                        Icon(
                            painter = painterResource(R.drawable.ic_more),
                            contentDescription = null,
                            tint = SoundoraColors.Neutral.Neutral01,
                            modifier = Modifier
                                .padding(start = 32.dp)
                                .size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_pause),
                    contentDescription = null,
                    tint = SoundoraColors.Primary.Primary,
                    modifier = Modifier
                        .size(56.dp)
                )
            }

            // Track

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(state.tracks.filterNotNull()) { track ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .clickable {
                                onTrackClick(track.preview)
                                Log.d("AlbumViewScreen", track.preview)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                        ) {
                            Text(
                                text = track.title,
                                color = SoundoraColors.Text.TextPrimary,
                                style = SoundoraTypography.Title.Medium.Medium
                            )

                            Row(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_download),
                                    contentDescription = null,
                                    tint = SoundoraColors.Primary.Primary,
                                    modifier = Modifier
                                        .size(12.dp)
                                )

                                Text(
                                    text = topAlbum.artistName,
                                    color = SoundoraColors.Neutral.Neutral01,
                                    style = SoundoraTypography.Body.Medium,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = painterResource(R.drawable.ic_more),
                            contentDescription = null,
                            tint = SoundoraColors.Neutral.Neutral01,
                            modifier = Modifier
                                .padding(start = 32.dp)
                                .size(20.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun AlbumViewScreenPreview() {
    AlbumViewScreenContent(
        dominantColor = SoundoraColors.BackGround.BackgroundPrimary,
        state = AlbumViewState(),
        topAlbum = TopAlbum(
            id = 1L,
            title = "Sample Album",
            link = "",
            cover = "",
            coverSmall = "",
            coverMedium = "",
            coverBig = "",
            coverXl = "",
            md5Image = "",
            recordType = "",
            trackList = "",
            explicitLyrics = true,
            position = 1,
            artistName = "Sample Artist",
            type = ""
        ),
        onBackClick = {},
        onTrackClick = {}
    )
}