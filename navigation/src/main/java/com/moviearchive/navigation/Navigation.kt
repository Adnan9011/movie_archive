package com.moviearchive.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_ID_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_IMAGE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_IS_LIKE_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_NUM_COMMENTS_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_NUM_LIKES_ARG
import com.moviearchive.navigation.DestinationsArgs.MOVIE_DETAIL_TITLE_ARG
import com.moviearchive.navigation.Screens.DETAIL_SCREEN
import com.moviearchive.navigation.Screens.HOME_SCREEN

private object Screens {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail"
}

object DestinationsArgs {
    const val MOVIE_DETAIL_ID_ARG = "id"
    const val MOVIE_DETAIL_TITLE_ARG = "title"
    const val MOVIE_DETAIL_IMAGE_ARG = "imageUrl"
    const val MOVIE_DETAIL_NUM_COMMENTS_ARG = "numComments"
    const val MOVIE_DETAIL_NUM_LIKES_ARG = "numLikes"
    const val MOVIE_DETAIL_IS_LIKE_ARG = "isLiked"
}

object Destinations {
    const val HOME_ROUT = HOME_SCREEN
    const val DETAIL_ROUT =
        "$DETAIL_SCREEN?" +
            "$MOVIE_DETAIL_ID_ARG={$MOVIE_DETAIL_ID_ARG}&" +
            "$MOVIE_DETAIL_TITLE_ARG={$MOVIE_DETAIL_TITLE_ARG}&" +
            "$MOVIE_DETAIL_IMAGE_ARG={$MOVIE_DETAIL_IMAGE_ARG}&" +
            "$MOVIE_DETAIL_NUM_COMMENTS_ARG={$MOVIE_DETAIL_NUM_COMMENTS_ARG}&" +
            "$MOVIE_DETAIL_NUM_LIKES_ARG={$MOVIE_DETAIL_NUM_LIKES_ARG}&" +
            "$MOVIE_DETAIL_IS_LIKE_ARG={$MOVIE_DETAIL_IS_LIKE_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(HOME_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDetail(movie: MovieUiModel) {
        navController.navigate(
            "$DETAIL_SCREEN?" +
                "${MOVIE_DETAIL_ID_ARG}=${movie.id}&" +
                "${MOVIE_DETAIL_TITLE_ARG}=${movie.title}&" +
                "${MOVIE_DETAIL_IMAGE_ARG}=${movie.imageUrl}&" +
                "${MOVIE_DETAIL_NUM_COMMENTS_ARG}=${movie.numComments}&" +
                "${MOVIE_DETAIL_NUM_LIKES_ARG}=${movie.numLikes}&" +
                "${MOVIE_DETAIL_IS_LIKE_ARG}=${movie.isLiked}"
        )
    }
}