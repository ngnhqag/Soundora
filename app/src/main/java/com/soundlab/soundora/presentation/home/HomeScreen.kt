package com.soundlab.soundora.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.soundlab.soundora.R
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToSetting: () -> Unit,
    navigateToAlbumView: (TopAlbum) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    Log.d("Home Screen", "${state.value.topAlbums.size}")

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeEvent.NavigateToSetting -> {
                    navigateToSetting()
                }
                is HomeEvent.NavigateToAlbumView -> {
                   navigateToAlbumView(event.topAlbum)
                }
            }
        }
    }

    HomeScreenContent(
        state = state.value,
        onSettingClick = {
            viewModel.processIntent(HomeIntent.OnSettingClick)
        },
        onAlbumClick = { topAlbum ->
            viewModel.processIntent(HomeIntent.OnAlbumClick(topAlbum))
        }
    )
}

@Composable
fun HomeScreenContent(
    state: HomeState,
    onSettingClick: () -> Unit,
    onAlbumClick: (TopAlbum) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.recently_played),
                color = SoundoraColors.Text.TextPrimary,
                style = SoundoraTypography.Title.Large.Bold,
                modifier = Modifier
                    .weight(1f)
            )

            IconButton(
                onClick = {
                    onSettingClick()
                },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = null,
                    tint = SoundoraColors.White,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            items(state.topAlbums.filterNotNull()) { topAlbum ->
               Column(
                   modifier = Modifier
                       .padding(start = 20.dp),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {

                   IconButton(
                       onClick = {
                           onAlbumClick(topAlbum)
                       },
                       modifier = Modifier.size(104.dp)
                   ) {
                       AsyncImage(
                           model = topAlbum.coverMedium,
                           contentDescription = topAlbum.title,
                           modifier = Modifier.size(104.dp)
                       )
                   }

                   val shortTitle = if (topAlbum.title.length > 13) {
                       topAlbum.title.substring(0, 13) + "..."
                   } else {
                       topAlbum.title
                   }

                   Text(
                       text = shortTitle,
                       modifier = Modifier
                           .padding( top = 8.dp ),
                       color = SoundoraColors.Text.TextPrimary,
                       style = SoundoraTypography.Body.Small.SemiBold
                   )
               }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        onSettingClick = {},
        state = HomeState(),
        onAlbumClick = {}
    )
}