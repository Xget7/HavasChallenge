package dev.xget.havasreddit.data.source

import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto
import dev.xget.havasreddit.domain.model.RedditPost

class FakeRedditLocalDataSource(private var post: RedditPost?) : RedditPostsLocalDataSourceI {

    override fun getSavedRedditPost(): RedditPost? {
        return post
    }

    override fun saveRedditPost(post: RedditPost) {
        this.post = post
    }
}