package com.moviearchive.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.navigation.Destinations
import com.moviearchive.navigation.DestinationsArgs
import com.moviearchive.navigation.NavigationActions

fun NavGraphBuilder.detail(
    modifier: Modifier,
    navActions: NavigationActions
) {
    composable(
        Destinations.DETAIL_ROUT,
        arguments = listOf(
            navArgument(DestinationsArgs.MOVIE_DETAIL_ID_ARG) { type = NavType.IntType }
        )
    ) { entry ->
        DetailScreen(
            modifier = modifier,
            movieId = entry.arguments?.getInt(DestinationsArgs.MOVIE_DETAIL_ID_ARG)!!,
            onBackClicked = {
                navActions.navigateToHome()
            }
        )
    }
}