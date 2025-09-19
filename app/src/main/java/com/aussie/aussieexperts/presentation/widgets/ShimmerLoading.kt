package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.shimmerLoading(
    durationMillis: Int = 1200,
    shimmerColors: List<Color> = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.9f),
    )
): Modifier {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnim by transition.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    return drawBehind {
        val width = size.width
        val height = size.height

        // Angle ~20° like Facebook shimmer
        val startOffset = Offset(x = translateAnim - width, y = 0f)
        val endOffset = Offset(x = translateAnim, y = height)

        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = startOffset,
            end = endOffset
        )

        drawRect(brush = brush)
    }
}