package com.example.squarerepos.navigation

sealed class Screen(val route: String) {
    data object ReposOverview: Screen(route = "repos_overview_screen")
}