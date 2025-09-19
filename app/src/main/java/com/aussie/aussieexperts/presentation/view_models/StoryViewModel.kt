package com.aussie.aussieexperts.presentation.view_models

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

    init {
        fetchStories()
    }

    fun fetchStories() {
        viewModelScope.launch {
            val result = repository.getStories()
            _stories.value = result
        }
    }
}