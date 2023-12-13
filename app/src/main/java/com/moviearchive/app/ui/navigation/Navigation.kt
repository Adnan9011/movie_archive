package com.moviearchive.app.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_DESCRIPTION_ARG
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_IMAGE_ARG
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_TITLE_ARG
import com.moviearchive.app.ui.navigation.Screens.DETAIL_SCREEN
import com.moviearchive.app.ui.navigation.Screens.HOME_SCREEN
import com.moviearchive.model.Movie

private object Screens {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_TITLE_ARG = "title"
    const val MOVIE_DETAIL_DESCRIPTION_ARG = "description"
    const val MOVIE_DETAIL_IMAGE_ARG = "image"
}

object Destinations {
    const val HOME_ROUT = HOME_SCREEN
    const val DETAIL_ROUT =
        "$DETAIL_SCREEN?$MOVIE_DETAIL_TITLE_ARG={$MOVIE_DETAIL_TITLE_ARG}&$MOVIE_DETAIL_IMAGE_ARG={$MOVIE_DETAIL_IMAGE_ARG}&$MOVIE_DETAIL_DESCRIPTION_ARG={$MOVIE_DETAIL_DESCRIPTION_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(HOME_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDetail(movie: Movie) {
        navController.navigate(
            "$DETAIL_SCREEN?${MOVIE_DETAIL_TITLE_ARG}=${movie.title}&${MOVIE_DETAIL_DESCRIPTION_ARG}=${movie.overview}&${MOVIE_DETAIL_IMAGE_ARG}=${movie.poster}"
        )
    }
}