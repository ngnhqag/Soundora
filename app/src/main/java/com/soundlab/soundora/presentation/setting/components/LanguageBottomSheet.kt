package com.soundlab.soundora.presentation.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.components.button.SoundoraButton
import com.soundlab.soundora.presentation.components.button.SoundoraButtonVariant
import com.soundlab.soundora.presentation.components.button.SoundoraIconButton
import com.soundlab.soundora.presentation.setting.model.LanguageOption
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(
    onDismissRequest: () -> Unit,
    languageCodeSelected: String,
    onSaveLanguage: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var languageSelected by remember { mutableStateOf(languageCodeSelected) }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        containerColor = SoundoraColors.BackGround.BackgroundPrimary,
    ) {
        LanguageBottomSheetContent(
            isSelectedLanguageChange = languageSelected != languageCodeSelected,
            languageSelected = languageSelected,
            onLanguageCodeSelectChange = { it ->
                languageSelected = it
            },
            onSaveLanguage = {
                onSaveLanguage(it)
            },
        )
    }
}

@Composable
fun LanguageBottomSheetContent(
    isSelectedLanguageChange: Boolean,
    languageSelected: String,
    onLanguageCodeSelectChange: (String) -> Unit,
    onSaveLanguage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary),
    ) {

        Text(
            text = stringResource(R.string.save),
            style = SoundoraTypography.Title.Medium.SemiBold,
            color = if (isSelectedLanguageChange) SoundoraColors.Text.TextPrimary else SoundoraColors.Text.TextTertiary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp)
                .clickable(enabled = isSelectedLanguageChange) {
                    onSaveLanguage(languageSelected)
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LanguageOption.entries.forEach { languageOption ->
            SoundoraButton(
                text = stringResource(languageOption.textRes),
                textStyle = SoundoraTypography.Title.Medium.SemiBold.copy(
                    color = SoundoraColors.Text.TextPrimary
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .heightIn(60.dp)
                    .then(
                        if (languageOption.languageCode == languageSelected) {
                            Modifier.border(
                                width = 1.dp,
                                color = SoundoraColors.White,
                                shape = SoundoraShapes.medium
                            )
                        } else {
                            Modifier
                        }
                    )
                    .clickable {
                        onLanguageCodeSelectChange(languageOption.languageCode)
                    },
                isContentCenter = false,
                variant = SoundoraButtonVariant.Filled(SoundoraColors.BackGround.BackgroundSecondary),
                leadingIcon = SoundoraIconButton(
                    iconRes = languageOption.iconRes, iconSize = 24.dp, iconTint = Color.Unspecified
                ),
                shape = SoundoraShapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Preview
@Composable
private fun LanguageBottomSheetPreview() {
    LanguageBottomSheetContent(
        isSelectedLanguageChange = true,
        modifier = Modifier.height(200.dp),
        languageSelected = "vi",
        onLanguageCodeSelectChange = {},
        onSaveLanguage = {}
    )
}