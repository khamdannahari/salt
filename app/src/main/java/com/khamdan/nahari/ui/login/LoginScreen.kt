package com.khamdan.nahari.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.khamdan.nahari.R
import com.khamdan.nahari.navigation.Screens
import com.khamdan.nahari.ui.theme.Purple500
import com.khamdan.nahari.ui.theme.Shapes


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val viewState by loginViewModel.state.collectAsState()

    if (viewState.navigate) {
        navController.navigate(Screens.Home.route)
        loginViewModel.doneNavigate()
    }

    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
        ) {
            //LOGO
            Icon(
                painter = painterResource(id = R.drawable.ic_send_24), contentDescription = "",
                modifier = Modifier
                    .padding(vertical = 40.dp, horizontal = 20.dp)
                    .width(40.dp)
                    .height(40.dp), tint = Color.White
            )

            Text(
                text = stringResource(id = R.string.login_title),
                modifier = Modifier.padding(horizontal = 20.dp),
                color = Color.White,
                fontSize = 45.sp,
                style = MaterialTheme.typography.h1
            )

            Text(
                text = stringResource(id = R.string.login_subtitle),
                modifier = Modifier.padding(horizontal = 20.dp),
                color = Color.White,
                fontSize = 16.sp,
                style = MaterialTheme.typography.body1
            )

            //CARDVIEW
            Card(
                shape = Shapes.medium, backgroundColor = Color.Black,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewState.email,
                        onValueChange = { loginViewModel.setEmail(it) },
                        placeholder = { Text(text = stringResource(id = R.string.login_email)) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Purple500,
                            unfocusedBorderColor = Color.White,
                            placeholderColor = Color.Gray,
                            textColor = Color.White
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        value = viewState.password,
                        onValueChange = { loginViewModel.setPassword(it) },
                        placeholder = { Text(text = stringResource(id = R.string.login_password)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Purple500,
                            unfocusedBorderColor = Color.White,
                            placeholderColor = Color.Gray,
                            textColor = Color.White
                        )
                    )

                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!viewState.loading) {
                            val gradient = Brush.horizontalGradient(
                                listOf(
                                    Color(142, 108, 252),
                                    Color(127, 193, 254)
                                )
                            )

                            GradientButton(
                                gradient = gradient,
                                onClick = { loginViewModel.login() }
                            )
                        } else {
                            CircularProgressIndicator()
                        }
                    }

                    Text(
                        text = viewState.message,
                        modifier = Modifier.padding(top = 10.dp),
                        color = Color.Red,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

            }
        }
    }
}


@Composable
fun GradientButton(
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    IconButton(
        modifier = modifier
            .size(64.dp)
            .background(gradient),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_send_24),
            contentDescription = "",
            tint = Color.White
        )
    }
}