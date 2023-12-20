package com.moviearchive.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.navigation.Destinations
import com.moviearchive.navigation.DestinationsArgs

fun NavGraphBuilder.detail(
    modifier: Modifier
) {
    composable(
        Destinations.DETAIL_ROUT,
        arguments = listOf(
            navArgument(DestinationsArgs.MOVIE_DETAIL_ID_ARG) { type = NavType.IntType },
            navArgument(DestinationsArgs.MOVIE_DETAIL_TITLE_ARG) { type = NavType.StringType },
            navArgument(DestinationsArgs.MOVIE_DETAIL_IMAGE_ARG) { type = NavType.StringType },
            navArgument(DestinationsArgs.MOVIE_DETAIL_NUM_COMMENTS_ARG) { type = NavType.IntType },
            navArgument(DestinationsArgs.MOVIE_DETAIL_NUM_LIKES_ARG) { type = NavType.IntType },
            navArgument(DestinationsArgs.MOVIE_DETAIL_IS_LIKE_ARG) { type = NavType.BoolType },

            )
    ) { entry ->
        DetailScreen(
            modifier = modifier,
            movie = MovieUiModel(
                id = entry.arguments?.getInt(DestinationsArgs.MOVIE_DETAIL_ID_ARG)!!,
                title = entry.arguments?.getString(DestinationsArgs.MOVIE_DETAIL_TITLE_ARG, "")!!,
                imageUrl = entry.arguments?.getString(
                    DestinationsArgs.MOVIE_DETAIL_IMAGE_ARG,
                    ""
                )!!,
                numComments = entry.arguments?.getInt(
                    DestinationsArgs.MOVIE_DETAIL_NUM_COMMENTS_ARG,
                    0
                )!!,
                numLikes = entry.arguments?.getInt(
                    DestinationsArgs.MOVIE_DETAIL_NUM_LIKES_ARG,
                    0
                )!!,
                isLiked = entry.arguments?.getBoolean(
                    DestinationsArgs.MOVIE_DETAIL_IS_LIKE_ARG,
                    false
                )!!
            )
        )
    }
}