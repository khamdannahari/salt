package com.khamdan.nahari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.khamdan.nahari.navigation.NavGraph
import com.khamdan.nahari.ui.theme.MainTheme
import com.khamdan.nahari.ui.home.HomeViewModel
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                val navController = rememberNavController()
                val homeViewModel: HomeViewModel by viewModel()
                NavGraph(
                    navController = navController,
                    homeViewModel = homeViewModel
                )
            }

        }
    }
}