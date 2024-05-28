package dev.xget.havasreddit.domain.repository.reddit

import dev.xget.havasreddit.domain.model.RedditPost

interface RedditPostsRepository {

    suspend fun getRedditPosts() : List<RedditPost>
}