package com.example.todo_app.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo_app.presentation.theme.ui.Secondary

@Composable
fun Circles(alphaLeft: Float = 0.4f, alphaRight: Float = 0.4f, color: Color = Secondary) {
    Canvas(modifier = Modifier) {
        drawCircle(
            color = color,
            radius = 95.dp.toPx(),
            center = Offset(x = 85.dp.toPx(), y = 0.dp.toPx()),
            alpha = alphaLeft
        )
        drawCircle(
            color = color,
            radius = 95.dp.toPx(),
            center = Offset(x = 10.dp.toPx(), y = 55.dp.toPx()),
            alpha = alphaRight
        )
    }
}