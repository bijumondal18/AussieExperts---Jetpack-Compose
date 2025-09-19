package com.aussie.aussieexperts.presentation.view_models

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

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            val result = repository.getPosts()
            _posts.value = result
        }
    }
}