package com.kvsSchool.kaverischool.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kvsSchool.kaverischool.States.inProgress
import com.kvsSchool.kaverischool.ui.kvsViewModel
import com.kvsSchool.kaverischool.util.CommonProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: kvsViewModel
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    //                   Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val classState = remember {
                mutableStateOf(TextFieldValue())
            }
            val sectionState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            val studentNameState = remember {
                mutableStateOf(TextFieldValue())
            }
            val parentsNameState = remember {
                mutableStateOf(TextFieldValue())
            }
            val parentsPhoneState = remember {
                mutableStateOf(TextFieldValue())
            }



            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(
                value = emailState.value,
                singleLine = true,
                onValueChange = {
                    emailState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                placeholder = {
                    Text(text = "Ex: abcd@gmail.com")
                },
                label = {
                    Text(
                        text = "Email",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )

            OutlinedTextField(
                value = passwordState.value,
                singleLine = true,
                onValueChange = {
                    passwordState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Password (Alphanumeric)",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                },
                placeholder = {
                    Text(text = "Ex: abcd1234")
                }
            )
            OutlinedTextField(
                value = studentNameState.value,
                singleLine = true,
                onValueChange = {
                    studentNameState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Student Name",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )
            OutlinedTextField(
                value = classState.value,
                singleLine = true,
                onValueChange = {
                    classState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Student's Class ",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )
            OutlinedTextField(
                value = sectionState.value,
                singleLine = true,
                onValueChange = {
                    sectionState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Student's Section ",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )



            OutlinedTextField(
                value = parentsNameState.value,
                singleLine = true,
                onValueChange = {
                    parentsNameState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Parent's Name",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )
            OutlinedTextField(
                value = parentsPhoneState.value,
                singleLine = true,
                onValueChange = {
                    parentsPhoneState.value = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(
                        text = "Parent's Number",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            )
            Button(
                onClick = {
//                    viewModel.signUp1(
//                        nameState.value.text.trim(),
//                        emailState.value.text.trim(),
//                        passwordState.value.text.trim(),
//                        section = ""
                  //      navController
           //         )
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "SIGN UP")
            }
            Text(
                text = "Already a user ? Go to login",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {

                    }
            )

        }
        if (inProgress.value) {
            CommonProgressBar()
        }
    }
}