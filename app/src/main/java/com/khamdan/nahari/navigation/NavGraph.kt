package com.khamdan.nahari.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khamdan.nahari.ui.login.LoginScreen
import com.khamdan.nahari.ui.login.LoginViewModel
import com.khamdan.nahari.ui.home.HomeViewModel
import com.khamdan.nahari.ui.home.HomeScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    )
    {
        composable(route = Screens.Login.route) {
            LoginScreen(navController, loginViewModel)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(homeViewModel)
        }
    }
}