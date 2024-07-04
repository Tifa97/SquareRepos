package com.example.squarerepos.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.squarerepos.viewmodel.ReposOverviewViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReposOverviewScreen(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<ReposOverviewViewModel>()
    
    Text(text = "Something to show on screen")
}