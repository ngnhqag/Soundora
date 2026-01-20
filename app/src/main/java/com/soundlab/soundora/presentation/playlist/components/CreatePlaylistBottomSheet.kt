package com.soundlab.soundora.presentation.playlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.presentation.components.button.SoundoraButton
import com.soundlab.soundora.presentation.components.button.SoundoraButtonVariant
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlaylistBottomSheetScreen(
    onDismiss : () -> Unit,
    onCreateClick : (String) -> Unit
) {
    var playlistName by rememberSaveable {
        mutableStateOf("")
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = SoundoraColors.BackGround.BackgroundPrimary
    ) {
        CreatePlaylistBottomSheetScreenContent(
            playlistName = playlistName,
            onPlaylistNameChange = { newName ->
                playlistName = newName
            },
            onCreatePlaylist = {
                onCreateClick(playlistName)
            }
        )
    }


}

@Composable
fun CreatePlaylistBottomSheetScreenContent(
    playlistName: String,
    onPlaylistNameChange: (String) -> Unit,
    onCreatePlaylist : () -> Unit
) {
    Column(
        modifier = Modifier
            .background(SoundoraColors.BackGround.BackgroundPrimary)
            .fillMaxWidth()
    ) {

        TextField(
            value = playlistName,
            textStyle = SoundoraTypography.Title.Medium.Medium.copy(
                color = SoundoraColors.Text.TextPrimary
            ),
            onValueChange = onPlaylistNameChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = SoundoraColors.Neutral.Neutral01,
                unfocusedIndicatorColor = SoundoraColors.Neutral.Neutral01,
                cursorColor = SoundoraColors.Neutral.Neutral01
            ),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "New Playlist",
                    color = SoundoraColors.Text.TextSecondary
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SoundoraButton(
            text = "Create playlist",
            textStyle = SoundoraTypography.Title.Medium.SemiBold.copy(
                color = SoundoraColors.Text.TextPrimary
            ),
            modifier = Modifier
                .heightIn(60.dp)
                .clickable {
                    onCreatePlaylist()
                },
            isContentCenter = true,
            variant = SoundoraButtonVariant.Filled(SoundoraColors.BackGround.BackgroundSecondary),
            shape = SoundoraShapes.medium,
            isFillMaxWidth = true
        )
    }
}

@Preview
@Composable
private fun CreatePlaylistBottomSheetScreenContentPreview() {
    CreatePlaylistBottomSheetScreenContent(
        onCreatePlaylist = {},
        playlistName = "",
        onPlaylistNameChange = {},
    )
}