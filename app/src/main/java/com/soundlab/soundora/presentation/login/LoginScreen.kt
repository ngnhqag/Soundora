package com.soundlab.soundora.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.components.IconButton
import com.soundlab.soundora.presentation.components.SoundoraButton
import com.soundlab.soundora.presentation.components.SoundoraButtonVariant
import com.soundlab.soundora.presentation.login.model.LoginScreenButton
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    LoginScreenContent(
        onSignUpClick = {
            viewModel.processIntent(LoginIntent.OnSignUpClick)
        },
        onGoogleClick = {
            viewModel.processIntent(LoginIntent.OnGoogleClick)
        },
        onFacebookClick = {
            viewModel.processIntent(LoginIntent.OnFacebookClick)
        },
    )
}

@Composable
fun LoginScreenContent(
    onSignUpClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
    ) {
        Image(
            painter = painterResource(R.drawable.img_login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_logo_white),
                contentDescription = null,
                tint = SoundoraColors.White,
                modifier = Modifier
                    .size(52.dp)
            )

            Text(
                text = stringResource(R.string.login_title),
                style = SoundoraTypography.Headline.Small.Bold.copy(
                    color = SoundoraColors.Text.TextPrimary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 20.dp)
            )

            SoundoraButton(
                text = stringResource(R.string.sign_up_free),
                textStyle = SoundoraTypography.Title.Medium.Bold.copy(
                    color = SoundoraColors.Black
                ),
                variant = SoundoraButtonVariant.Filled(
                    backgroundColor = SoundoraColors.Primary.Primary
                ),
                shape = SoundoraShapes.extraLarge,
                strokeWidth = 1.dp,
                isFillMaxWidth = true,
                modifier = Modifier
                    .padding(top = 24.dp, start = 44.dp, end = 44.dp)
                    .clickable(onClick = onSignUpClick)
            )

            LoginScreenButton.entries.forEach { loginScreenButton ->
                SoundoraButton(
                    text = stringResource(loginScreenButton.text),
                    textStyle = SoundoraTypography.Title.Medium.Bold.copy(
                        color = SoundoraColors.Text.TextPrimary
                    ),
                    isContentCenter = false,
                    variant = SoundoraButtonVariant.Outline(
                        strokeColor = SoundoraColors.Stroke.StrokePrimary,
                        backgroundColor = Color.Transparent
                    ),
                    leadingIcon = IconButton(
                        iconRes = loginScreenButton.iconRes,
                        iconSize = 16.dp,
                        iconTint = Color.Unspecified
                    ),
                    shape = SoundoraShapes.extraLarge,
                    strokeWidth = 1.dp,
                    isFillMaxWidth = true,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 44.dp, end = 44.dp)
                        .then(
                            when (loginScreenButton) {
                                LoginScreenButton.GOOGLE -> Modifier.clickable(onClick = onGoogleClick)
                                LoginScreenButton.FACEBOOK -> Modifier.clickable(onClick = onFacebookClick)
                            }
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenContentPreview() {
    LoginScreenContent(
        onSignUpClick = {},
        onGoogleClick = {},
        onFacebookClick = {}
    )
}

