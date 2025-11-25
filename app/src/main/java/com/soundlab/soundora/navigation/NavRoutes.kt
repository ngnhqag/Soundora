package com.soundlab.soundora.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.soundlab.soundora.presentation.login.LoginScreen
import com.soundlab.soundora.presentation.main.MainScreen
import com.soundlab.soundora.presentation.setting.SettingScreen
import com.soundlab.soundora.presentation.splash.SplashScreen
import com.soundlab.soundora.util.Constant
import com.soundlab.soundora.util.ext.replaceLastWith
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack(Destination.Splash)
    val mainTabRequest = remember { MutableStateFlow<Int?>(null) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Destination.Login> {
                LoginScreen(
                    navigateToMain = {
                        backStack.replaceLastWith(Destination.Main)
                    }
                )
            }

            entry<Destination.Main> {
                MainScreen(
                    navigateToSetting = {
                        backStack.add(Destination.Setting)
                    },
                    tabRequestFlow = mainTabRequest,
                    onTabRequestConsumed = {
                        mainTabRequest.value = null
                    }
                )
            }

            entry<Destination.Splash> {
                SplashScreen(
                    navigateToMain = {
                        backStack.replaceLastWith(Destination.Main)
                    },
                    navigateToLogin = {
                        backStack.replaceLastWith(Destination.Login)
                    }
                )
            }

            entry<Destination.Setting> {
                SettingScreen(
                    navigateToHome = {
                        backStack.removeLastOrNull()
                    },
                    navigateToLibrary = {
                        mainTabRequest.value = Constant.MainTabIndex.LIBRARY
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}
