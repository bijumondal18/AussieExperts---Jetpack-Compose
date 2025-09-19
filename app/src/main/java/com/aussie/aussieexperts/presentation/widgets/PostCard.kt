package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.utils.DateTimeUtils

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
    onLikeClick: (Post) -> Unit = {},
    onPostClick: (Post) -> Unit = {},
    onCommentClick: (Post) -> Unit = {}
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onPostClick(post) }
            .navigationBarsPadding()
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            // Header with avatar + username
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                BaseCircleAvatar(
                    imageUrl = post.senderImage,
                    initials = post.initials,
                    size = 40.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = post.senderName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = DateTimeUtils.formatTimestamp(post.sent.toLong()),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Post image
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.image)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerLoading()
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Actions + likes
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "❤️ ${post.likes}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.clickable { onLikeClick(post) }
                    )
                    Text(
                        text = "💬 Comment",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.clickable { onCommentClick(post) }
                    )
                }
            }

            // Caption
            if (!post.text.isNullOrBlank()) {
                Text(
                    text = post.text,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                )
            }

        }
    }
}