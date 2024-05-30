package dev.xget.havasreddit.data.local.reddit_posts

import dev.xget.havasreddit.domain.model.RedditPost

interface RedditPostsLocalDataSourceI {
    fun getSavedRedditPost() : RedditPost?
    fun saveRedditPost(post: RedditPost)
}