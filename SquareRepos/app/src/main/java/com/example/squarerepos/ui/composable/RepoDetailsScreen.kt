package com.example.squarerepos.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.squarerepos.viewmodel.RepoDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepoDetailsScreen(repoName: String?, navController: NavController, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<RepoDetailsViewModel>()
    val repo by remember { viewModel.repo }
    val isLoading by remember { viewModel.isLoading }

    if (repo == null) {
        repoName?.let {
            viewModel.getRepoByName(repoName)
        }
    }

    if (isLoading) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        Text(text = "Something to show for repo ${repo?.name}")
    }
}