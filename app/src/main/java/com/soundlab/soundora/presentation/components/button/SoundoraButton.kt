package com.soundlab.soundora.presentation.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.soundlab.soundora.R
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.presentation.theme.SoundoraShapes
import com.soundlab.soundora.presentation.theme.SoundoraTypography

@Composable
fun SoundoraButton(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    isContentCenter: Boolean = true,
    variant: SoundoraButtonVariant = SoundoraButtonVariant.Filled(SoundoraColors.Primary.Primary),
    leadingIcon: SoundoraIconButton? = null,
    trailingIcon: SoundoraIconButton? = null,
    shape: Shape = SoundoraShapes.extraLarge,
    strokeWidth: Dp = 2.dp,
    isFillMaxWidth: Boolean = true
) {
    val backgroundColor = when (variant) {
        is SoundoraButtonVariant.Filled -> {
            variant.backgroundColor
        }
        is SoundoraButtonVariant.Outline -> {
            variant.backgroundColor
        }
    }

    val strokeColor = when (variant) {
        is SoundoraButtonVariant.Filled -> {
            null
        }
        is SoundoraButtonVariant.Outline -> {
            variant.strokeColor
        }
    }


    Row(
        modifier = modifier
            .then(
                if (isFillMaxWidth) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                }
            )
            .heightIn(
                min = 48.dp
            )
            .then(
                if (strokeColor != null) {
                    Modifier.border(
                        width = strokeWidth,
                        color = strokeColor,
                        shape = shape
                    )
                } else {
                    Modifier.clip(shape)
                }
            )
            .background(backgroundColor)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isContentCenter) Arrangement.Center else Arrangement.Absolute.Left
    ) {
        if (leadingIcon != null) {
            Icon(
                painter = painterResource(leadingIcon.iconRes),
                contentDescription = null,
                tint = leadingIcon.iconTint,
                modifier = Modifier
                    .padding(leadingIcon.iconPadding)
                    .size(leadingIcon.iconSize)
            )
        }

        Text(
            text = text,
            style = textStyle
        )

        if (trailingIcon != null) {
            Icon(
                painter = painterResource(trailingIcon.iconRes),
                contentDescription = null,
                tint = trailingIcon.iconTint,
                modifier = Modifier
                    .padding(trailingIcon.iconPadding)
                    .size(trailingIcon.iconSize)
            )
        }
    }
}

data class SoundoraIconButton(
    val iconRes: Int,
    val iconSize: Dp,
    val iconTint: Color,
    val iconPadding: PaddingValues = PaddingValues(horizontal = 12.dp)
)

sealed class SoundoraButtonVariant {
    data class Filled(val backgroundColor: Color) : SoundoraButtonVariant()
    data class Outline(val strokeColor: Color, val backgroundColor: Color) : SoundoraButtonVariant()
}

@Preview(showBackground = true)
@Composable
private fun SoundoraButtonPreview() {
    SoundoraButton(
        text = "Button",
        textStyle = SoundoraTypography.Title.Medium.Bold.copy(
            color = SoundoraColors.Text.TextPrimary
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        isFillMaxWidth = false,
        leadingIcon = SoundoraIconButton(
            iconRes = R.drawable.ic_logo_white,
            iconSize = 24.dp,
            iconTint = SoundoraColors.White
        ),
        trailingIcon = SoundoraIconButton(
            iconRes = R.drawable.ic_logo,
            iconSize = 24.dp,
            iconTint = Color.White
        )
    )
}