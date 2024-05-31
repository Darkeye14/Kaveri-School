package com.kvsSchool.kaverischool.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kvsSchool.kaverischool.States.signIn
import com.kvsSchool.kaverischool.data.recievingPost
import com.kvsSchool.kaverischool.ui.kvsViewModel

@Composable
fun CommonProgressBar() {
    Row(
        modifier = Modifier
            .alpha(0.5f)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(enabled = false) {}
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun CheckSignedIn(
    viewModel: kvsViewModel,
    navController: NavController
) {
    val alreadySignedIn = remember { mutableStateOf(false) }
    val signIn = signIn.value
    if (signIn && !alreadySignedIn.value) {
        alreadySignedIn.value = true
//        LaunchedEffect(key1 = Unit) {
//            navController.navigate(DestinationScreen.HomeScreen.route) {
//                popUpTo(0)
//            }
//        }
//
//    } else if (!signIn) {
//        LaunchedEffect(key1 = Unit) {
//            navController.navigate(DestinationScreen.SignUp.route) {
//                popUpTo(0)
//            }
//        }
    }
}

fun navigateTo(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        popUpTo(route)
        launchSingleTop = true
    }
}

@Composable
fun PostCard(
    post: recievingPost,
    onItemClick: () -> Unit,
    asyncImages: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
            .clickable {
                onItemClick.invoke()
            },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),


            ) {
            asyncImages.invoke()

            Text(
                text = post.title ?: "     Attention!!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                modifier = Modifier
                    .padding(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = post.timeStamp ?: "recently",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(12.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }
}