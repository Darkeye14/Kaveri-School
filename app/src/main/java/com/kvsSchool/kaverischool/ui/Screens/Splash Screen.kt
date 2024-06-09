package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kvsSchool.kaverischool.ui.kvsViewModel
import com.kvsSchool.kaverischool.ui.theme.hex
import com.kvsSchool.kaverischool.util.CheckSignedIn

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: kvsViewModel
) {
    Box(
        modifier = Modifier
            .background(hex)
            .fillMaxSize(),
    ) {
        CheckSignedIn(
            viewModel = viewModel,
            navController = navController
        )

    }
}
