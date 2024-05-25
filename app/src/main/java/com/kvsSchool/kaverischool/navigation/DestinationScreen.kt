package com.kvsSchool.kaverischool.navigation

sealed class DestinationScreen(var route : String ) {

    data object SignUp :DestinationScreen("Signup")
    data object Login :DestinationScreen("Login")
    data object SplashScreen:DestinationScreen("splashScreen")
    data object HomeScreen:DestinationScreen("homeScreen")
}