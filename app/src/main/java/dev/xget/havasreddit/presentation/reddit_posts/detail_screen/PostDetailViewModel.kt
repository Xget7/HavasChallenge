package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostDetailViewModel(
    private val repository: RedditPostsRepository
) : ViewModel() {


    private val _state = MutableStateFlow(PostDetailsScreenUiState())
    val state = _state.asStateFlow()

    init {
        getSavedPost()
    }

    private fun getSavedPost() {
        repository.getSavedRedditPost()?.let { post ->
            Log.d("PostDetailViewModel", "getSavedPost: $post")
            _state.update {
                it.copy(
                    redditPost = post
                )
            }
        }
    }
}

