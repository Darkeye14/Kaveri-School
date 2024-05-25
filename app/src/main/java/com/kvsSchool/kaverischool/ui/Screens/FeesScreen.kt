package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kvsSchool.kaverischool.R
import com.kvsSchool.kaverischool.ui.theme.hex
import com.kvsSchool.kaverischool.util.CheckSignedIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeesScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 38.sp,
                        fontFamily = FontFamily.Cursive
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

            Image(
                painter = painterResource(id = R.drawable.kaveri_qr),
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .height(500.dp)
                    .fillMaxWidth()
            )

        }
    }
}