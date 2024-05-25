package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kvsSchool.kaverischool.R
import com.kvsSchool.kaverischool.ui.kvsViewModel
import com.kvsSchool.kaverischool.ui.theme.hex
import com.kvsSchool.kaverischool.util.CheckSignedIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController : NavController,
    viewModel: kvsViewModel,
    modifier: Modifier = Modifier
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

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.padding(8.dp)) {
                    HomeScreenCard(Modifier.weight(1f), text = "Student Performance") {
//                        viewModel.getMyProfilesData()
//                        navigateTo(navController, DestinationScreen.MyProfilesScreen.route)
                    }
                    HomeScreenCard(Modifier.weight(1f), text = "Announcements") {

       //                 navigateTo(navController, DestinationScreen.SavedScreen.route)
                    }
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    HomeScreenCard(Modifier.weight(1f), text = "About Us") {
//                        viewModel.getMyProfilesData()
//                        navigateTo(navController, DestinationScreen.MyProfilesScreen.route)
                    }
                    HomeScreenCard(Modifier.weight(1f), text = "Fees Payment") {

       //                 navigateTo(navController, DestinationScreen.SavedScreen.route)
                    }
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    HomeScreenCard(Modifier.weight(1f), text = "Message Us") {
//                        viewModel.getMyProfilesData()
//                        navigateTo(navController, DestinationScreen.MyProfilesScreen.route)
                    }
                    HomeScreenCard(Modifier.weight(1f), text = "Contact Us") {

       //                 navigateTo(navController, DestinationScreen.SavedScreen.route)
                    }
                }

            }


        }
    }
}


@Composable
fun HomeScreenCard(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Card(modifier = modifier
        .height(250.dp)
        .clickable {
            onClick.invoke()
        }
        .padding(8.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.cardColors(hex)) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                text = text,
                maxLines = 2,
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}