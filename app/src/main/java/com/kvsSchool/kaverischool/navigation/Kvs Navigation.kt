package com.kvsSchool.kaverischool.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kvsSchool.kaverischool.ui.Screens.AboutScreen
import com.kvsSchool.kaverischool.ui.Screens.ContactScreen
import com.kvsSchool.kaverischool.ui.Screens.FeesScreen
import com.kvsSchool.kaverischool.ui.Screens.HomeScreen
import com.kvsSchool.kaverischool.ui.Screens.SignUpScreen
import com.kvsSchool.kaverischool.ui.Screens.SplashScreen
import com.kvsSchool.kaverischool.ui.kvsViewModel

@Composable
fun KvsNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<kvsViewModel>()

    NavHost(
        navController = navController ,
        startDestination = DestinationScreen.HomeScreen.route
    ) {
        composable(DestinationScreen.SignUp.route){
            SignUpScreen(navController = navController, viewModel = viewModel)
        }
        composable(DestinationScreen.SplashScreen.route){
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(DestinationScreen.HomeScreen.route){
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(DestinationScreen.AboutScreen.route){
            AboutScreen()
        }
        composable(DestinationScreen.ContactScreen.route){
            ContactScreen()
        }
        composable(DestinationScreen.FeesScreen.route){
            FeesScreen()
        }
    }

}