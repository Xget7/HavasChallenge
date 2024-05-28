package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

data class PostDetailsScreenUiState(
    val isLoading: Boolean = false,
    val post: Any? = null,
    val error: String? = null
)
