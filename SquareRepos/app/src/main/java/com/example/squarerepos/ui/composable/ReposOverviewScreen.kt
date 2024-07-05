package com.example.squarerepos.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.squarerepos.navigation.Screen
import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.viewmodel.ReposOverviewViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReposOverviewScreen(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<ReposOverviewViewModel>()
    val repos by remember { viewModel.repos }
    val isLoading by remember { viewModel.isLoading }

    if (isLoading) {
        LoadingIndicator(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            repos?.let { repoList ->
                items(repoList.size, itemContent = {
                    RepoListItem(repo = repoList[it], navController)
                })
            }
        }
    }
}

@Composable
fun RepoListItem(repo: ReposResponseItem, navController: NavController, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(12.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable {
                navController.navigate(
                    "${Screen.RepoDetails.route}"
                )
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            SubcomposeAsyncImage(
                contentScale = ContentScale.FillBounds,
                model = repo.owner?.avatar_url,
                contentDescription = repo.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f),
                loading = {
                    LoadingIndicator(modifier = modifier.fillMaxSize())
                }
            )
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = repo.name ?: "", fontWeight = FontWeight.Bold)
                Text(text = repo.description ?: "")
            }
        }
    }
}