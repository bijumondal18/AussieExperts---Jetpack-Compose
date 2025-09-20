package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.presentation.view_models.PostViewModel
import com.aussie.aussieexperts.presentation.view_models.StoryViewModel

@Composable
fun PostsColumn(
    posts: List<Post>,
    isLoading: Boolean = false,
    onPostClick: (Post) -> Unit = {}
) {

    if(isLoading){
        repeat(5){
            PostShimmerItem()
        }
    }else{
        posts.forEach {post ->
            PostCard(post = post, onPostClick = onPostClick)
        }
    }


}


@Composable
fun PostShimmerItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
//            .clip(RoundedCornerShape(12.dp))
            .height(300.dp) // approximate height
            .shimmerLoading()
    ) {}
}