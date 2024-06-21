package com.example.quizy

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(viewModel: QuizViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route){
        composable(Screen.WelcomeScreen.route){
            Welcome(viewModel = viewModel, navController = navController)
        }
        composable(Screen.HomeScreen.route){
            HomeScreen(viewModel,navController)
        }
        composable(Screen.FinalScreen.route){
            FinalScreen(viewModel = viewModel,navController=navController)
        }

    }
}