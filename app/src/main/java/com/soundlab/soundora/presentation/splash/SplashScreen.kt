package com.soundlab.soundora.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SplashEvent.NavigateToLogin -> {
                    navigateToLogin()
                }

                SplashEvent.NavigateToMain -> {
                    navigateToMain()
                }
            }
        }
    }

    SplashScreenContent()
}
@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(88.dp)
        )

        Text(
            text = stringResource(R.string.app_name),
            color = SoundoraColors.Primary.Primary,
            style = SoundoraTypography.Headline.Medium.Bold
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreenContent()
}