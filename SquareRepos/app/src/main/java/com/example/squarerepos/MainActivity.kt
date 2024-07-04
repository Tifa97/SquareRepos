package com.example.squarerepos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.squarerepos.navigation.Screen
import com.example.squarerepos.navigation.SquareAppNavGraph
import com.example.squarerepos.ui.theme.SquareReposTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareReposTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SquareAppNavGraph(navController = navController, startDestination = Screen.ReposOverview.route)
            }
        }
    }
}