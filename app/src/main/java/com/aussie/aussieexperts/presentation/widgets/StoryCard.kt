package com.aussie.aussieexperts.presentation.widgets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.data.Story

@Composable
fun StoryCard(
    modifier: Modifier = Modifier,
    story: Story,
    onStoryClick: (Story) -> Unit = {}
) {

    LaunchedEffect(story.image) {
        Log.d("StoryCard", "Rendering story: ${story.id}, image=${story.image}")
    }

    val context = LocalContext.current

    val request = remember(story.image) {
        ImageRequest.Builder(context)
            .data(story.image)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    }

    Box(
        modifier = Modifier
            .width(130.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onStoryClick(story) }
    ) {
        // Story background image
        SubcomposeAsyncImage(
            model = request,
            contentDescription = "Story Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerLoading()
                )
            }
        )

        // Profile avatar (top-left)
        BaseCircleAvatar(
            imageUrl = story.senderImage,
            initials = story.initials,
            size = 36.dp,
            borderWidth = 2.dp,
            borderColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            onClick = { onStoryClick(story) }
        )

        // Username (bottom-left)
        Text(
            text = story.senderName,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
            ),
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}