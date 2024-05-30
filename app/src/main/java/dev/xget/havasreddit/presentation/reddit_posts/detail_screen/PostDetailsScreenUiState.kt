package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

import dev.xget.havasreddit.domain.model.RedditPost

data class PostDetailsScreenUiState(
    val isLoading: Boolean = false,
    val post: Any? = null,
    val error: String? = null,
    val redditPost: RedditPost? = null
)
