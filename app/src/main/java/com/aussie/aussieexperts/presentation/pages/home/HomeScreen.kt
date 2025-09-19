package com.aussie.aussieexperts.presentation.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aussie.aussieexperts.core.base.BaseCard
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.core.base.BaseIconButton
import com.aussie.aussieexperts.core.base.BaseScaffold
import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.presentation.widgets.PostCard
import com.aussie.aussieexperts.presentation.widgets.StoriesRow

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    BaseScaffold(
        title = "Aussie Experts",
        showBack = false,
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp),
            ) {
                BaseIconButton(
                    icon = Icons.Default.UploadFile,
                    contentDescription = "Upload Files",
                    modifier = Modifier,
                    onClick = {
                        println("Upload Files")
                    }
                )
                Text(
                    text = "Upload Files",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
        content = {
            HomeFeed(
                modifier = Modifier.fillMaxSize().padding(it),
                onStoryClick = { println("Clicked story: ${it.senderName}") },
                onPostClick = { println("Liked post: ${it.senderName}") },
                onPostLikeClick = { println("Liked post: ${it.senderName}") },
                onPostCommentClick = { println("Comment on post: ${it.senderName}") }
            )
        }
    )
}