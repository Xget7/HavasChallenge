package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.xget.havasreddit.model.reddit.RedditPostsRepository

class PostDetailViewModel (
    private val repository: RedditPostsRepository
) : ViewModel() {

}

