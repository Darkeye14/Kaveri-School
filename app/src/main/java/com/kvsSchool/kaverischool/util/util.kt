package com.kvsSchool.kaverischool.util

import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kvsSchool.kaverischool.States.toastState
import com.kvsSchool.kaverischool.data.Announcement
import com.kvsSchool.kaverischool.data.ToggleableInfo
import com.kvsSchool.kaverischool.data.recievingPost
import com.kvsSchool.kaverischool.navigation.DestinationScreen
import com.kvsSchool.kaverischool.ui.kvsViewModel
import com.kvsSchool.kaverischool.ui.theme.hex

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
    val signIn = viewModel.signIn.value
    if (signIn && !alreadySignedIn.value) {
        alreadySignedIn.value = true
        LaunchedEffect(key1 = Unit) {
            navController.navigate(DestinationScreen.HomeScreen.route) {
                popUpTo(0)
            }
        }

    } else if (!signIn) {
        LaunchedEffect(key1 = Unit) {
            navController.navigate(DestinationScreen.SignUp.route) {
                popUpTo(0)
            }
        }
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
fun OnToastMessage(
    modifier: Modifier = Modifier
) {
    if (toastState.value)
        Toast.makeText(LocalContext.current, "Please Fill All Fields", Toast.LENGTH_LONG).show()
    toastState.value = false
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

@Composable
fun AnnouncementsCard(
    post: Announcement,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            ) {

            Text(
                text = "     Attention!!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                modifier = Modifier
                    .padding(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = post.text ?: "Empty",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(12.dp)
                )
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

@Composable
fun SelectClass(modifier: Modifier = Modifier): String {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(

                isChecked = false,
                text = "PreKG"
            ),
            ToggleableInfo(

                isChecked = false,
                text = "LKG"
            ),
            ToggleableInfo(

                isChecked = false,
                text = "UKG"
            ),
            ToggleableInfo(

                isChecked = false,
                text = "1"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "2"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "3"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "4"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "5"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "6"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "7"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "8"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "9"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "10"
            ),

            )
    }
    val classState = remember {
        mutableStateOf("")
    }
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(text = "OK")
                }
            },
            title = {
                Text(text = "Select Class", fontWeight = FontWeight.Bold)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            containerColor = hex,
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                ) {


                    radioButtons.forEachIndexed { index, info ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    radioButtons.replaceAll {
                                        it.copy(
                                            isChecked = it.text == info.text
                                        )
                                    }
                                }
                                .padding(end = 16.dp)
                        ) {

                            RadioButton(
                                selected = info.isChecked,
                                onClick = {
                                    radioButtons.replaceAll {
                                        it.copy(
                                            isChecked = it.text == info.text
                                        )
                                    }
                                    classState.value = info.text
                                }
                            )
                            Text(
                                text = info.text,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold

                            )
                        }
                    }
                }

            },
        )
    }
    return classState.value
}