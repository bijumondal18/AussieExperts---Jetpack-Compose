package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.presentation.view_models.StoryViewModel

@Composable
fun StoriesRow(
    modifier: Modifier = Modifier,
    viewModel: StoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    isLoading: Boolean = false,
    onStoryClick: (Story) -> Unit = {}
) {

    val stories by viewModel.stories.collectAsState()

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        when{
            isLoading ->{
                items(5){
                    StoryShimmerItem()
                }
            }
            stories.isNotEmpty() -> {
                items(stories) { story ->
                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .height(220.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { onStoryClick(story) }
                    ) {
                        // Story background image
                        SubcomposeAsyncImage (
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(story.image)
                                .crossfade(true)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .build(),
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
            }
        }

    }
}

@Composable
fun StoryShimmerItem() {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .shimmerLoading()
    )
}
