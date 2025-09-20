package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
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
    val listState = rememberLazyListState()


    // Load initial page
    LaunchedEffect(Unit) {
        viewModel.fetchStories()
    }

    // Detect when reaching near end of list
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size }
            .collect { visible ->
                if (visible >= stories.size - 2) { // near end
                    viewModel.fetchStories()
                }
            }
    }



    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        when {
            isLoading -> {
                items(5) {
                    StoryShimmerItem()
                }
            }

            stories.isNotEmpty() -> {
                items(
                    items = stories,
                    key = { story -> story.id }
                ) { story ->
                    StoryCard(story = story, onStoryClick = onStoryClick)
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
