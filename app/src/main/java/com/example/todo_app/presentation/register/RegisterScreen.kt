package com.example.todo_app.presentation.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.todo_app.core.Screen
import com.example.todo_app.core.UserViewModel
import com.example.todo_app.presentation.components.Circles
import com.example.todo_app.presentation.theme.ui.Background
import com.example.todo_app.presentation.theme.ui.Primary
import com.example.todo_app.presentation.theme.ui.TextColor
import com.example.todo_app.presentation.theme.ui.TextFieldColor

@Composable
fun RegisterScreen(
    context: Context = LocalContext.current,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("")}

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
            Spacer(Modifier.height(200.dp))
            Text(
                text = "Welcome Onboard!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "Lets help you in completing your tasks",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(30.dp))

            var text by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                // TEXT FIELDS

                Text(
                    text = "Full name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    value = name,
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(23.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = TextFieldColor
                    )
                )
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
                        .padding(bottom = 10.dp),
                    value = email,
                    textStyle = TextStyle(
                      fontSize = 20.sp
                    ),
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
                        .padding(bottom = 10.dp),
                    value = password,
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(23.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = TextFieldColor
                    )
                )
                Text(
                    text = "Confirm password",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),
                    value = confirmPassword,
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    onValueChange = { confirmPassword = it },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(23.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = TextFieldColor
                    )
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary,
                                contentColor = Color.Black
                            ),
                            onClick = {
                                      if (name.isBlank() || email.isBlank() || password.isBlank()) {
                                          Toast.makeText(context, "Please fill fields!", Toast.LENGTH_SHORT).show()
                                          return@Button
                                      }

                                if (confirmPassword != password) {
                                    Toast.makeText(context, "Incorrect confirmed password!", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                userViewModel.saveUser(email, name, password)
                                navController.navigate(Screen.DashboardScreen.route)
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(60.dp)
                        ) {
                            Text(
                                text = "Register",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    val annotatedText = buildAnnotatedString {
                        append("Already have an account ? ")
                        pushStringAnnotation(tag = "SignIn", annotation = "SignIn")
                        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.Bold)) {
                            append("Sign In")
                        }
                        pop()
                    }

                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            annotatedText.getStringAnnotations(tag = "SignIn", start = offset, end = offset)
                                .firstOrNull()?.run {
                                    run { navController.navigate(Screen.LoginScreen.route) }
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