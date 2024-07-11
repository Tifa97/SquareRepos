package com.example.squarerepos.navigation

// class that holds routes and arguments for navigation
sealed class Screen(val route: String) {
    data object ReposOverview: Screen(route = "repos_overview_screen")
    data object RepoDetails: Screen(route = "repo_details_screen")

    companion object {
        const val REPO_NAME_ARG = "repoName"
    }
}