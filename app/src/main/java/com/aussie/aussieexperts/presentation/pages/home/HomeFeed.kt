package com.aussie.aussieexperts.presentation.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.presentation.view_models.PostViewModel
import com.aussie.aussieexperts.presentation.view_models.StoryViewModel
import com.aussie.aussieexperts.presentation.widgets.CreatePostCard
import com.aussie.aussieexperts.presentation.widgets.PostCard
import com.aussie.aussieexperts.presentation.widgets.PostShimmerItem
import com.aussie.aussieexperts.presentation.widgets.PostsColumn
import com.aussie.aussieexperts.presentation.widgets.StoriesRow

@Composable
fun HomeFeed(
    modifier: Modifier = Modifier,
    storyViewModel: StoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    postViewModel: PostViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onStoryClick: (Story) -> Unit = {},
    onPostClick: (Post) -> Unit = {},
    onPostLikeClick: (Post) -> Unit = {},
    onPostCommentClick: (Post) -> Unit = {}
) {

    val stories by storyViewModel.stories.collectAsState()
    val posts by postViewModel.posts.collectAsState()
    val listState = rememberLazyListState()


    // Load first page
    LaunchedEffect(Unit) {
        postViewModel.fetchPosts()
    }


    // Detect scroll to end
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size }
            .collect { visible ->
                if (visible >= posts.size - 2) {
                    postViewModel.fetchPosts()
                }
            }
    }



    LazyColumn(
        modifier = modifier.background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
        state = listState,
    ) {
        // What's on your mind card at top
        item {
            CreatePostCard()
        }

        // Stories Row in the middle
        item {
            Spacer(modifier = Modifier.height(16.dp))
            StoriesRow(onStoryClick = onStoryClick, isLoading = stories.isEmpty())
        }

        // Posts below
        items(posts) { post ->
            PostCard(post = post, onPostClick = onPostClick)
        }

        // Loading shimmer at bottom
        if (posts.isEmpty()) {
            items(5) {
                PostShimmerItem()
            }
        }
    }
}