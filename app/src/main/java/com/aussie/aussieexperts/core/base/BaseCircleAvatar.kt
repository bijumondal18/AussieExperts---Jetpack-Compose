package com.aussie.aussieexperts.core.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BaseCircleAvatar(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    painter: Painter? = null,                // optional custom painter (local resource)
    initials: String? = null,                // fallback initials (e.g., "AB")
    size: Dp = 36.dp,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentDescription: String? = "Profile image",
    isOnline: Boolean = false,               // optional small badge
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(size)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .let {
                    if (borderWidth > 0.dp) it.border(BorderStroke(borderWidth, borderColor), CircleShape)
                    else it
                },
            shape = CircleShape,
            color = backgroundColor,
            tonalElevation = 0.dp
        ) {
            // content inside the circle
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when {
                    painter != null -> {
                        androidx.compose.foundation.Image(
                            painter = painter,
                            contentDescription = contentDescription,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    !imageUrl.isNullOrBlank() -> {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = contentDescription,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            placeholder = null,
                            error = null
                        )
                    }
                    else -> {
                        // initials fallback
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = initials?.let { if (it.length > 2) it.take(2) else it } ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = (size.value / 2.8).sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // Online/offline indicator (bottom-right)
        if (isOnline) {
            val badgeSize = (size.value / 4).dp.coerceAtLeast(8.dp)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (badgeSize / 6), y = (badgeSize / 6))
                    .size(badgeSize)
                    .clip(CircleShape)
                    .background(Color(0xFF4CAF50)) // green
                    .border(BorderStroke(1.dp, Color.White), CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseCircleAvatarPreview() {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        BaseCircleAvatar(
            imageUrl = null,
            initials = "AB",
            size = 72.dp,
            borderWidth = 2.dp,
            borderColor = Color.Gray,
            isOnline = true
        )
        BaseCircleAvatar(
            imageUrl = "https://picsum.photos/200", // preview may not load in preview, but in app it will
            initials = "CD",
            size = 48.dp
        )
    }
}