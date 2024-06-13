package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kvsSchool.kaverischool.R
import com.kvsSchool.kaverischool.ui.theme.hex
import com.kvsSchool.kaverischool.util.CheckSignedIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

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
                    titleContentColor = Color.White
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Display(textName = "History of the Institution", text = stringResource(id = R.string.history))
                Display(textName = "Foreword by the Administrator - KVSHS", text = stringResource(id = R.string.Foreword_Administrator))
                Display(textName = "Foreword by the Headmistress – LKG to 10th", text = stringResource(id = R.string.Foreword_Headmistress))
                Display(textName = "Learning Where Merit and Values Converge", text = stringResource(id = R.string.Learning_where_Merit_and_Values))

                
                
            }

        }
    }
}


@Composable
fun Display(
    textName: String,
    text: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(hex)
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            Text(
                text = textName,
                modifier = Modifier
                    .padding(12.dp),
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,

                )
            Text(
                    text = text,
                    modifier = Modifier
                        .padding(16.dp),
                    fontFamily = FontFamily.SansSerif,
                color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    minLines = 1

                )
        }
    }
}