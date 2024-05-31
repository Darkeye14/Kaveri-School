package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kvsSchool.kaverischool.R
import com.kvsSchool.kaverischool.States.imageUriList
import com.kvsSchool.kaverischool.States.postsDataList
import com.kvsSchool.kaverischool.navigation.DestinationScreen
import com.kvsSchool.kaverischool.ui.kvsViewModel
import com.kvsSchool.kaverischool.ui.theme.hex
import com.kvsSchool.kaverischool.util.PostCard
import com.kvsSchool.kaverischool.util.navigateTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: kvsViewModel
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 38.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = hex,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {

        }
    ) {
        viewModel.populatePost()
        imageUriList.clear()
        val posts = postsDataList.value

        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(it)
                .fillMaxHeight()
                .fillMaxSize(),
        ) {
            Image(
                alpha = 0.40f,
                painter = painterResource(id = R.drawable.whatsapp_kaveri),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            if (posts.isEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Card(
                        onClick = { },
                        colors = CardDefaults.cardColors(hex)
                    ) {

                        Text(
                            text = "No Profiles Available",
                            fontSize = 25.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(color = Color.Transparent)
                ) {
                    items(posts) { post ->
                        imageUriList.clear()
                        PostCard(
                            post = post,
                            onItemClick = {

                                post.uid?.let { it1 ->
                                    DestinationScreen.SinglePostScreen.createRoute(
                                        id = it1
                                    )
                                }
                                    ?.let { it2 ->
                                        navigateTo(
                                            navController,
                                            it2
                                        )
                                    }
                            },
                            asyncImages = {

                                AsyncImage(
                                    model = R.drawable.education,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .background(Color.LightGray)
                                        .fillMaxWidth()
                                        .size(175.dp)
                                        .padding(12.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Inside
                                )
                            }
                        )
                    }

                }
            }


        }
    }
}