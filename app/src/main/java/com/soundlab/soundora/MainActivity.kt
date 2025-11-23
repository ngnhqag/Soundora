package com.soundlab.soundora

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.soundlab.soundora.navigation.NavRoutes
import com.soundlab.soundora.presentation.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupTheme(window)
        setContent {
            NavRoutes()
        }
    }
}

fun setupTheme(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)

    // Status bar transparent
    window.statusBarColor = android.graphics.Color.TRANSPARENT

    // Icon status bar màu trắng
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.insetsController?.setSystemBarsAppearance(
            0, // xóa flag LIGHT_STATUS_BAR → icon trắng
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        // fallback cho Android < 11
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}