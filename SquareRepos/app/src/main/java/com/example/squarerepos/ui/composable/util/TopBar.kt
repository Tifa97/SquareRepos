package com.example.squarerepos.ui.composable.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.squarerepos.R

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.squarerepos),
    shouldShowShadow: Boolean = true,
) {
    Column(modifier = modifier
        .fillMaxWidth(),
    ) {
        Box (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f),
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.Center)
            )
        }
        if (shouldShowShadow) {
            BottomShadow()
        }
    }
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 8.dp) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black.copy(alpha = alpha),
                    Color.Transparent,
                )
            )
        )
    )
}