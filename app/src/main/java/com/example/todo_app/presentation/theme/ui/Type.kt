package com.example.todo_app.presentation.theme.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app.R

val poppinsBold = FontFamily(listOf(
    Font(R.font.poppins_bold)
))

val poppinsSemiBold = FontFamily(listOf(
    Font(R.font.poppins_semi_bold)
))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsBold,
        fontSize = 25.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = poppinsSemiBold,
        fontSize = 15.sp,
        color = TextColor,
        lineHeight = 20.sp,
    )
)