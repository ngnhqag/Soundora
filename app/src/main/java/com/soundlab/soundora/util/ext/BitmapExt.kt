package com.soundlab.soundora.util.ext

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import com.soundlab.soundora.presentation.theme.SoundoraColors

suspend fun String.getDominantColor(
    context: Context,
    defaultColor: Color = SoundoraColors.BackGround.BackgroundPrimary
): Color {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(this)
        .allowHardware(false) // ⚡ bắt buộc để bitmap có thể dùng Palette
        .build()

    val result = loader.execute(request)
    if (result is SuccessResult) {
        // result.image là coil3.Image
        // chuyển sang Android Bitmap trên CPU
        val bitmap: Bitmap = result.image.toBitmap().copy(Bitmap.Config.ARGB_8888, true)

        val dominant = Palette.from(bitmap)
            .generate()
            .getDominantColor(defaultColor.toArgb())
        return Color(dominant)
    }
    return defaultColor
}
