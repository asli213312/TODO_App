package com.example.todo_app.presentation.splash_screen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo_app.R
import com.example.todo_app.core.Screen
import com.example.todo_app.presentation.components.Circles
import com.example.todo_app.presentation.theme.ui.Background
import com.example.todo_app.presentation.theme.ui.Primary
import com.example.todo_app.presentation.theme.ui.TextColor
import kotlinx.coroutines.launch

@Composable()
fun SplashScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Circles()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Image(
                painter = painterResource(R.drawable.get_started_splash),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier.size(180.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Get things done with TODO",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 5.dp),
            )
            Text(
                text = "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit. " +
                    "Sed posuere gravida purus id eu " +
                    "condimentum est diam quam. " +
                    "Condimentum blandit diam.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 55.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 75.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = Color.Black
                    ),
                    onClick = {
                          coroutineScope.launch {
                              navController.navigate(Screen.RegisterScreen.route)
                          }
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .size(60.dp)
                ) {
                    Text(
                        text = "Get Started",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}