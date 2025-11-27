package com.soundlab.soundora.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToSetting: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                HomeEvent.NavigateToSetting -> {
                    navigateToSetting()
                }
            }
        }
    }

    HomeScreenContent(
        onSettingClick = {
            viewModel.processIntent(HomeIntent.OnSettingClick)
        }
    )
}

@Composable
fun HomeScreenContent(
    onSettingClick: () -> Unit
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
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        onSettingClick = {
            
        }
    )
}