package com.mayurdw.fibretracker.ui.destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.model.domain.HomeState
import com.mayurdw.fibretracker.ui.screens.HomeScreenLayout
import com.mayurdw.fibretracker.ui.screens.core.LoadingScreen
import com.mayurdw.fibretracker.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>(),
    onCardSelected: (id: Int) -> Unit
) {
    val homeState by viewModel.homeStateFlow.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    LaunchedEffect(viewModel) {
        viewModel.getLatestData()
    }

    when (homeState) {
        is HomeState.Success -> {
            val homeData = homeState as HomeState.Success
            HomeScreenLayout(
                modifier = modifier,
                homeData = homeData.data,
                onNextClicked = { viewModel.onDateChanged(false) },
                onPreviousClicked = { viewModel.onDateChanged(true) }) {
                onCardSelected(it)
            }
        }

        is HomeState.Loading -> {
            LoadingScreen(modifier)
        }

        else -> {

        }
    }
}
