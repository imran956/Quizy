package com.example.quizy

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_Screen")
    object FinalScreen : Screen("final_Screen")
    object WelcomeScreen:Screen(route = "welcome_screen")
}