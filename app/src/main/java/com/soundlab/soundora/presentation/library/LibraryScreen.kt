package com.soundlab.soundora.presentation.library

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.components.button.SoundoraButton
import com.soundlab.soundora.presentation.components.button.SoundoraButtonVariant
import com.soundlab.soundora.presentation.components.view.VerticalGradientBox
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LibraryScreenContent(
        state = state.value,
    )
}

@Composable
fun LibraryScreenContent(
    state: LibraryState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SoundoraColors.BackGround.BackgroundPrimary)
    ) {
        VerticalGradientBox(
            colorTop = SoundoraColors.BackGround.BackgroundOnTopLibrary,
            colorBottom = SoundoraColors.BackGround.BackgroundPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .height(444.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 44.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.user?.displayUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.img_avatar)
                    .error(R.drawable.img_avatar)
                    .fallback(R.drawable.img_avatar)
                    .size(150)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(122.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            SoundoraButton(
                text = stringResource(R.string.edit_profile),
                textStyle = SoundoraTypography.Body.Small.SemiBold.copy(
                    color = SoundoraColors.Text.TextPrimary
                ),
                modifier = Modifier
                    .padding(top = 36.dp)
                    .height(32.dp)
                    .align(Alignment.CenterHorizontally),
                isContentCenter = true,
                variant = SoundoraButtonVariant.Filled(SoundoraColors.Neutral.Neutral02),
                shape = SoundoraShapes.extraLarge,
                isFillMaxWidth = false
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 44.dp, end = 44.dp, top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.playlistCount.toString(),
                        color = SoundoraColors.Text.TextPrimary,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp),

                        )

                    Text(
                        text = stringResource(R.string.playlists).uppercase(),
                        color = SoundoraColors.Neutral.Neutral01,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.followerCount.toString(),
                        color = SoundoraColors.Text.TextPrimary,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp),

                        )

                    Text(
                        text = stringResource(R.string.followers).uppercase(),
                        color = SoundoraColors.Neutral.Neutral01,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.followingCount.toString(),
                        color = SoundoraColors.Text.TextPrimary,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp),

                        )

                    Text(
                        text = stringResource(R.string.followings).uppercase(),
                        color = SoundoraColors.Neutral.Neutral01,
                        style = SoundoraTypography.Body.Small.Medium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }
            }

            Text(
                text = stringResource(R.string.playlists),
                color = SoundoraColors.Text.TextPrimary,
                style = SoundoraTypography.Title2.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp)
            )

            state.playLists.forEach { playlist ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(playlist.thumbnail.toUri())
                            .crossfade(true)
                            .placeholder(R.drawable.img_playlist_thumbnail)
                            .error(R.drawable.img_playlist_thumbnail)
                            .fallback(R.drawable.img_playlist_thumbnail)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(52.dp)
                            .clip(SoundoraShapes.extraLarge)
                    )

                    Column(
                        modifier = Modifier

                    ) {  }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LibraryScreenPreview() {
    LibraryScreenContent(
        state = LibraryState()
    )
}