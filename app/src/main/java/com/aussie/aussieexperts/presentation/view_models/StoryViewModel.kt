package com.aussie.aussieexperts.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aussie.aussieexperts.data.Story
import com.aussie.aussieexperts.domain.repositories.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryViewModel(
    private val repository: StoryRepository = StoryRepository()
) : ViewModel() {

    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories

    private var isLoading = false
    private var hasMore = true


    fun fetchStories(limit: Long = 10) {
        if (isLoading || !hasMore) return

        viewModelScope.launch {
            isLoading = true
            val newStories = repository.getStories(limit)

            newStories.forEach { story ->
                Log.d("StoryViewModel", "Fetched story: ${story.id}, image=${story.image}")
            }

            if (newStories.isEmpty()) {
                hasMore = false
            } else {
                _stories.value = _stories.value + newStories
            }
            isLoading = false
        }
    }

    fun refreshStories(limit: Long = 10) {
        repository.resetPagination()
        _stories.value = emptyList()
        hasMore = true
        fetchStories(limit)
    }

}