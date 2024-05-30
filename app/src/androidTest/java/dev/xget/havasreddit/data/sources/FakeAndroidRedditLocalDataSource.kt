package dev.xget.havasreddit.data.sources

import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.domain.model.RedditPost

class FakeAndroidRedditLocalDataSource(private var post: RedditPost?) : RedditPostsLocalDataSourceI {

    override fun getSavedRedditPost(): RedditPost? {
        return post
    }

    override fun saveRedditPost(post: RedditPost) {
        this.post = post
    }
}