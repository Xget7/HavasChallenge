package dev.xget.havasreddit.data.repository.reddit

import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository

class FakeRedditPostRepository(
    private val posts: List<RedditPost>?
) : RedditPostsRepository {

    private var savedPost : RedditPost? = null

    override suspend fun getRedditPosts(): List<RedditPost> {
        return posts ?: throw Exception("No posts")
    }

    override fun saveRedditPost(redditPost: RedditPost) {
        savedPost = redditPost
    }

    override fun getSavedRedditPost(): RedditPost? {
        return savedPost
    }
}