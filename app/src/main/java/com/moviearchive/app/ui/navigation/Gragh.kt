package com.moviearchive.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_DESCRIPTION_ARG
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_IMAGE_ARG
import com.moviearchive.app.ui.navigation.DestinationsArgs.MOVIE_DETAIL_TITLE_ARG
import com.moviearchive.feature.presentation.detail.DetailScreen
import com.moviearchive.model.Movie

//import gmail.adnan9011.moviesample.presentation.home.HomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_ROUT,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Destinations.HOME_ROUT) {
//            HomeScreen(
//                modifier = Modifier,
//                onShowDetail = { movie ->
//                    navActions.navigateToDetail(movie)
//                }
//            )
        }

        composable(
            Destinations.DETAIL_ROUT,
            arguments = listOf(
                navArgument(MOVIE_DETAIL_TITLE_ARG) { type = NavType.StringType },
                navArgument(MOVIE_DETAIL_DESCRIPTION_ARG) { type = NavType.StringType },
                navArgument(MOVIE_DETAIL_IMAGE_ARG) { type = NavType.StringType }
            )
        ) { entry ->
            DetailScreen(
                modifier = modifier,
                movie = Movie(
                    title = entry.arguments?.getString(MOVIE_DETAIL_TITLE_ARG, "")!!,
                    overview = entry.arguments?.getString(MOVIE_DETAIL_DESCRIPTION_ARG, "")!!,
                    poster = entry.arguments?.getString(MOVIE_DETAIL_IMAGE_ARG, "")!!
                )
            )
        }
    }
}