package com.soundlab.soundora.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.soundlab.soundora.presentation.theme.SoundoraColors

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
        Text(
            text = "Search Screen",
            color = SoundoraColors.Text.TextPrimary
        )
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreenContent()
}