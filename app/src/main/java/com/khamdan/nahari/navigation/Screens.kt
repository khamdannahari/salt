package com.khamdan.nahari.navigation


sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
}