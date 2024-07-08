package com.example.squarerepos.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.squarerepos.R
import com.example.squarerepos.viewmodel.RepoDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepoDetailsScreen(repoName: String?, navController: NavController, modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<RepoDetailsViewModel>()
    val repo by remember { viewModel.repo }
    val isLoading by remember { viewModel.isLoading }

    val uriHandler = LocalUriHandler.current

    LaunchedEffect(repoName) {
        repoName?.let {
            viewModel.getRepoByName(repoName)
        }
    }

    if (isLoading) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(text = repo?.name ?: "")
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f),
                model = repo?.owner?.avatar_url,
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight(0.8f)
            ){
                repo?.full_name?.let { fullName ->
                    RepoDetailsSection(
                        title = stringResource(R.string.full_name),
                        text = fullName
                    )
                }

                repo?.owner?.login?.let { owner ->
                    RepoDetailsSection(
                        title = stringResource(R.string.owner),
                        text = owner
                    )
                }

                repo?.owner?.type?.let { type ->
                    RepoDetailsSection(
                        title = stringResource(R.string.owner_type),
                        text = type
                    )
                }

                repo?.language?.let { language ->
                    RepoDetailsSection(
                        title = stringResource(R.string.language),
                        text = language
                    )
                }

                repo?.private?.let { isPrivate ->
                    RepoDetailsSection(
                        title = stringResource(R.string.is_private),
                        text = isPrivate.toString()
                    )
                }

                repo?.description?.let { description ->
                    RepoDetailsSection(
                        title = stringResource(R.string.description),
                        text =  description,
                        isLast = true
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        repo?.html_url?.let {
                            uriHandler.openUri(it)
                        }
                    }) {
                    Text(text = stringResource(R.string.open_repo_in_github))
                }
            }
        }
    }
}

@Composable
fun RepoDetailsSection(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    isLink: Boolean = false,
    isLast: Boolean = false
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier,
        color = if (isLink) Color.Blue else Color.Black
    )
    if (!isLast) {
        Spacer(modifier = Modifier
            .padding(vertical = 4.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}