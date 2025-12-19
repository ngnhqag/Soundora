package com.soundlab.soundora.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.soundlab.soundora.presentation.albumview.AlbumViewScreen
import com.soundlab.soundora.presentation.components.bottomsheet.MusicBottomSheet
import com.soundlab.soundora.presentation.login.LoginScreen
import com.soundlab.soundora.presentation.main.MainScreen
import com.soundlab.soundora.presentation.player.PlayerIntent
import com.soundlab.soundora.presentation.player.PlayerViewModel
import com.soundlab.soundora.presentation.setting.SettingScreen
import com.soundlab.soundora.presentation.splash.SplashScreen
import com.soundlab.soundora.util.ext.replaceLastWith
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavRoutes(
    playerViewModel: PlayerViewModel = koinViewModel()
) {
    val backStack = rememberNavBackStack(Destination.Splash)
    val playerState by playerViewModel.viewState.collectAsStateWithLifecycle()
    val isPlaying = playerState.isPlaying

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
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
                        navigateToAlbumView = { topAlbum ->
                            backStack.add(Destination.AlbumView(topAlbum))
                        },
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
                    )
                }

                entry<Destination.AlbumView> { dest ->
                    AlbumViewScreen(
                        topAlbum = dest.topAlbum,
                        navigateToMain = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
            }
        )
        if (isPlaying) {
            playerState.currentTrack?.let {
                MusicBottomSheet(
                    modifier = Modifier
                        .padding(bottom = 80.dp),
                    track = it,
                    isPaused = playerState.isPaused,
                    onStopClick = {
                        playerViewModel.processIntent(PlayerIntent.Stop)
                    },
                    onPlayPauseClick = {
                        playerViewModel.processIntent(PlayerIntent.TogglePlayPause)
                    },
                )
            }
        }
    }
}
