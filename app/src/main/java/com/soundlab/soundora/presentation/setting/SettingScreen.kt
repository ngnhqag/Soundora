package com.soundlab.soundora.presentation.setting

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.setting.model.SettingOption
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingScreen(
    navigateToHome: () -> Unit,
    navigateToLibrary: () -> Unit,
    viewModel: SettingViewModel = koinViewModel()
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()
    Log.d("SettingScreen", "state: ${state.value.user}")
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SettingEvent.NavigateToHome -> {
                    navigateToHome()
                }
                SettingEvent.NavigateToLibrary -> {
                    navigateToLibrary()
                }
            }
        }
    }

    SettingScreenContent(
        state = state.value,
        onBackClick = {
            viewModel.processIntent(SettingIntent.OnBackClick)
        },
        onOptionClick = { settingOption ->
            viewModel.processIntent(SettingIntent.OnOptionClick(settingOption))
        },
        onProfileClick = { 
            viewModel.processIntent(SettingIntent.OnProfileClick)
        }
    )
}

@Composable
fun SettingScreenContent(
    state: SettingState,
    onBackClick: () -> Unit,
    onOptionClick: (SettingOption) -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            IconButton(
                onClick = {
                    onBackClick()
                },
                modifier = Modifier
                    .padding(start = 32.dp)
                    .size(16.dp)
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = SoundoraColors.Neutral.Neutral00,
                    modifier = Modifier,
                )
            }

            Text(
                text = stringResource(R.string.settings),
                color = SoundoraColors.Text.TextPrimary,
                style = SoundoraTypography.Title.Medium.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onProfileClick() }
                .padding(top = 52.dp)
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
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
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = state.user?.name ?: stringResource(R.string.name),
                    style = SoundoraTypography.Title.Medium.Bold,
                    color = SoundoraColors.Neutral.Neutral00,
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                )
                Text(
                    text = stringResource(R.string.view_profile),
                    color = SoundoraColors.Neutral.Neutral01,
                    style = SoundoraTypography.Body.Medium,
                    modifier = Modifier

                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_navigation),
                contentDescription = null,
                tint = SoundoraColors.White,
                modifier = Modifier
                    .size(16.dp),
            )
        }

        Spacer(
            modifier = Modifier
                .padding(top = 56.dp)
        )

        SettingOption.entries.forEach { settingOption ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        onOptionClick(settingOption)
                    }
                    .padding(horizontal = 28.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(settingOption.text),
                    color = SoundoraColors.White,
                    style = SoundoraTypography.Title.Medium.Medium,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_navigation),
                    contentDescription = null,
                    tint = SoundoraColors.White,
                    modifier = Modifier
                        .size(16.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreenContent(
        state = SettingState(),
        onBackClick = {},
        onOptionClick = {},
        onProfileClick = {}
    )
}