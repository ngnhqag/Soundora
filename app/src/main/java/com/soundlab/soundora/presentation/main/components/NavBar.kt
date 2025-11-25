package com.soundlab.soundora.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.presentation.main.model.MainTab
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@Composable
fun NavBar(
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tabSelected: Int = 0
) {
    Row(
        modifier = modifier
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .heightIn(80.dp)
            .fillMaxWidth()
    ) {
        MainTab.entries.forEach { mainTab ->
            val selectedColor = if (mainTab.index == tabSelected) SoundoraColors.MainTab.Selected else SoundoraColors.MainTab.UnSelected
            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ){
                        onTabClick(mainTab.index)
                    }
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(mainTab.iconRes),
                    contentDescription = null,
                    tint = selectedColor,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 2.dp)
                        .size(20.dp)
                )

                Text(
                    text = stringResource(mainTab.textRes),
                    style = SoundoraTypography.Title.Small.Medium,
                    color = selectedColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavBarPreview() {
    NavBar(
        onTabClick = {}
    )
}