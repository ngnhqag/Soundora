package com.soundlab.soundora.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@Composable
fun SearchScreen() {
    SearchScreenContent()
}

@Composable
fun SearchScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 28.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Search",
                color = SoundoraColors.Text.TextPrimary,
                style = SoundoraTypography.Headline.Medium.Bold,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = null,
                tint = SoundoraColors.White,
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreenContent()
}