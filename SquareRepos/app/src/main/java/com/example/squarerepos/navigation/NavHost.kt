package com.example.squarerepos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.squarerepos.ui.composable.RepoDetailsScreen
import com.example.squarerepos.ui.composable.ReposOverviewScreen

// Composable that takes care of navigation.
@Composable
fun SquareAppNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.ReposOverview.route) {
            ReposOverviewScreen(navController = navController)
        }
        composable(
            route = "${Screen.RepoDetails.route}/{${Screen.REPO_NAME_ARG}}",
            arguments = listOf(
                navArgument(Screen.REPO_NAME_ARG) {
                    type = NavType.StringType
                }
            )
        ) {
            val repoName = remember {
                it.arguments?.getString(Screen.REPO_NAME_ARG)
            }
            RepoDetailsScreen(repoName = repoName, navController = navController)
        }
    }
}