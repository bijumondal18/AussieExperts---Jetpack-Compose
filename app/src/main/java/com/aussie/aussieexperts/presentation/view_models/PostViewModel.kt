package com.aussie.aussieexperts.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.domain.repositories.PostRepository
import com.aussie.aussieexperts.domain.repositories.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PostViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private var isLoading = false
    private var hasMore = true


    fun fetchPosts(limit: Long = 10) {
        if (isLoading || !hasMore) return

        viewModelScope.launch {
            isLoading = true
            val newPosts = repository.getPosts(limit)

            newPosts.forEach { post ->
                Log.d("PostViewModel", "Fetched Post: ${post.id}, image=${post.image}")
            }

            if (newPosts.isEmpty()) {
                hasMore = false
            } else {
                _posts.value = _posts.value + newPosts
            }
            isLoading = false
        }

    }

    fun refreshPosts(limit: Long = 10) {
        repository.resetPagination()
        _posts.value = emptyList()
        hasMore = true
        fetchPosts(limit)
    }

}