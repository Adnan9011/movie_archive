package com.moviearchive.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_ID_ARG
import com.moviearchive.navigation.Screens.DETAIL_SCREEN
import com.moviearchive.navigation.Screens.HOME_SCREEN

private object Screens {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_ID_ARG = "id"
}

object Destinations {
    const val HOME_ROUT = HOME_SCREEN
    const val DETAIL_ROUT =
        "$DETAIL_SCREEN?" +
            "$MOVIE_DETAIL_ID_ARG={$MOVIE_DETAIL_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(HOME_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDetail(movieId: Int) {
        navController.navigate(
            "$DETAIL_SCREEN?" +
                "${MOVIE_DETAIL_ID_ARG}=${movieId}"
        )
    }
}