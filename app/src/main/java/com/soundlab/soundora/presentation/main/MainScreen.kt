package com.soundlab.soundora.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soundlab.soundora.presentation.home.HomeScreen
import com.soundlab.soundora.presentation.library.LibraryScreen
import com.soundlab.soundora.presentation.main.components.NavBar
import com.soundlab.soundora.presentation.search.SearchScreen
import com.soundlab.soundora.presentation.theme.SoundoraColors
import com.soundlab.soundora.util.AppEventManager
import com.soundlab.soundora.util.Constant
import com.soundlab.soundora.util.AppEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navigateToSetting: () -> Unit,
    viewModel: MainViewModel = koinViewModel(),
) {
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                MainEvent.NavigateToSetting -> {
                    navigateToSetting()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        AppEventManager.events.collect { event ->
            when (event) {
                is AppEvent.NavigateToLibrary -> {
                    viewModel.processIntent(MainIntent.OnTabClick(event.tabSelected))
                }
            }
        }
    }

    MainScreenContent(
        state = state.value,
        onTabClick = { index ->
            viewModel.processIntent(MainIntent.OnTabClick(index))
        },
        navigateToSetting = {
            viewModel.processIntent(MainIntent.NavigateToSetting)
        }
    )
}
@Composable
fun MainScreenContent(
    state: MainState,
    navigateToSetting: () -> Unit,
    onTabClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SoundoraColors.BackGround.BackgroundPrimary)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
    ) {
        MainContent(
            navigateToSetting = {
                navigateToSetting()
            },
            tabSelected = state.tabSelected,
            modifier = Modifier
                .weight(1f)
        )
        NavBar(
            onTabClick = { index ->
                onTabClick(index)
            },
            modifier = Modifier
                .fillMaxWidth(),
            tabSelected = state.tabSelected
        )
    }
}

@Composable
fun MainContent(
    navigateToSetting: () -> Unit,
    modifier: Modifier = Modifier,
    tabSelected: Int = 0
) {
    Column(
        modifier = modifier
    ) {
        when (tabSelected) {
            Constant.MainTabIndex.HOME -> HomeScreen(
                navigateToSetting = {
                    navigateToSetting()
                },
            )
            Constant.MainTabIndex.SEARCH -> SearchScreen()
            Constant.MainTabIndex.LIBRARY -> LibraryScreen()
        }
    }
}
@Preview
@Composable
private fun MainScreenPreview() {
    MainScreenContent(
        state = MainState(),
        onTabClick = {},
        navigateToSetting = {}
    )
}