package com.kvsSchool.kaverischool.navigation

sealed class DestinationScreen(var route : String ) {

    data object SignUp :DestinationScreen("Signup")
    data object Login :DestinationScreen("Login")
    data object AllImageScreen :DestinationScreen("allImagesScreen")
    data object SplashScreen:DestinationScreen("splashScreen")
    data object HomeScreen:DestinationScreen("homeScreen")
    data object AboutScreen:DestinationScreen("aboutScreen")
    data object ContactScreen:DestinationScreen("contactScreen")
    data object FeesScreen:DestinationScreen("feesScreen")
    data object PostsScreen :DestinationScreen("PostsScreen")
    data object AnnouncementsScreen :DestinationScreen("announcementsScreen")
    data object SinglePostScreen:DestinationScreen("singlePostScreen/{postId}"){
        fun createRoute(id : String) = "singlePostScreen/$id"
    }
}