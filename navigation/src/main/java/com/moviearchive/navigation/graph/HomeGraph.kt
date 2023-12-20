package com.moviearchive.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moviearchive.feature.presentation.home.HomeScreen
import com.moviearchive.navigation.Destinations
import com.moviearchive.navigation.NavigationActions

fun NavGraphBuilder.home(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(Destinations.HOME_ROUT) {
        HomeScreen(
            modifier = modifier,
            onShowDetail = { movie ->
                navActions.navigateToDetail(movie)
            }
        )
    }
}