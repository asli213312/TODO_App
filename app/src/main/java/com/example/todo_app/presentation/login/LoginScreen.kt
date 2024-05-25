package com.example.todo_app.presentation.login

import android.text.style.ClickableSpan
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo_app.R
import com.example.todo_app.core.Screen
import com.example.todo_app.core.UserData
import com.example.todo_app.presentation.components.Circles
import com.example.todo_app.presentation.theme.ui.Background
import com.example.todo_app.presentation.theme.ui.Primary
import com.example.todo_app.presentation.theme.ui.TextColor
import com.example.todo_app.presentation.theme.ui.TextFieldColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    userData: UserData,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Circles()
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(150.dp))
            Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Spacer(Modifier.height(30.dp))
            Image(
                painter = painterResource(R.drawable.login_img),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier.size(180.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                // TEXT FIELDS

                Text(
                    text = "Email",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .size(50.dp),
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(23.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = TextFieldColor
                    )
                )
                Text(
                    text = "Password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .size(50.dp),
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(23.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = TextFieldColor
                    )
                )

                val annotatedForgotPassword = buildAnnotatedString {
                    pushStringAnnotation(tag = "ForgotPass", annotation = "ForgotPass")
                    withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.ExtraBold)) {
                        append("Forgot password?")
                    }
                    pop()
                }

                ClickableText(
                    modifier = Modifier.align(Alignment.End),
                    text = annotatedForgotPassword,
                    onHover = { },
                    style = MaterialTheme.typography.bodyLarge,
                    onClick = { offset ->
                        annotatedForgotPassword.getStringAnnotations(
                            tag = "ForgotPass",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.run {}
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary,
                                contentColor = Color.Black
                            ),
                            onClick = {
                                  if (email.isBlank() || password.isBlank())
                                      return@Button

                                if (password != UserData.PASSWORD_KEY.name)
                                    return@Button

                                navController.navigate(Screen.DashboardScreen.route)
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(60.dp)
                        ) {
                            Text(
                                text = "Login",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    val annotatedText = buildAnnotatedString {
                        append("Don't have an account ? ")
                        pushStringAnnotation(tag = "SignUp", annotation = "SignUp")
                        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.Bold)) {
                            append("Sign Up")
                        }
                        pop()
                    }

                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            annotatedText.getStringAnnotations(tag = "SignUp", start = offset, end = offset)
                                .firstOrNull()?.run {
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 55.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextColor
                        )
                    )
                }
            }
        }
    }
}