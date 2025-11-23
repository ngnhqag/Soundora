package com.soundlab.soundora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.soundlab.soundora.presentation.login.LoginScreen
import com.soundlab.soundora.presentation.main.MainScreen
import com.soundlab.soundora.presentation.splash.SplashScreen
import com.soundlab.soundora.util.ext.replaceLastWith

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack(Destination.Splash)

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
                MainScreen()
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
        }
    )
}
