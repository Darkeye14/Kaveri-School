package com.kvsSchool.kaverischool.navigation

import AnnouncementScreen
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
import com.kvsSchool.kaverischool.ui.Screens.LoginScreen
import com.kvsSchool.kaverischool.ui.Screens.PostsScreen
import com.kvsSchool.kaverischool.ui.Screens.SinglePostScreen
import com.kvsSchool.kaverischool.ui.Screens.SplashScreen
import com.kvsSchool.kaverischool.ui.Screens.ViewAllPicsScreen
import com.kvsSchool.kaverischool.ui.kvsViewModel

@Composable
fun KvsNavigation() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<kvsViewModel>()

    NavHost(
        navController = navController ,
        startDestination = DestinationScreen.Login.route
    ) {
        composable(DestinationScreen.Login.route){
            LoginScreen(navController = navController, viewModel = viewModel)
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
        composable(DestinationScreen.AllImageScreen.route){
            ViewAllPicsScreen(viewModel = viewModel)
        }
        composable(DestinationScreen.PostsScreen.route){
            PostsScreen(navController = navController, viewModel = viewModel)
        }
        composable(DestinationScreen.AnnouncementsScreen.route){
            AnnouncementScreen(navController = navController, viewModel = viewModel)
        }
        composable(DestinationScreen.SinglePostScreen.route){
            val postId = it.arguments?.getString("postId")
            postId?.let {
                SinglePostScreen(postId,viewModel)
            }

        }
    }

}