package com.example.randomanimalfacts.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomanimalfacts.ui.UserInputViewModel

@Composable
fun RandomAnimalFactsNavigationGraph(UserInputViewModel: UserInputViewModel= viewModel()){
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = Routes.USER_INPUT_SCREEN){
        composable(Routes.USER_INPUT_SCREEN){
            UserInputScreen(UserInputViewModel)
        }

        composable(Routes.WELCOME_SCREEN){
            WelcomeScreen()
        }


    }
    
}