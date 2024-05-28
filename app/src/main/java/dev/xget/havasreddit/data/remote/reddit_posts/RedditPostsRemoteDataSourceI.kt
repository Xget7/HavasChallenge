package dev.xget.havasreddit.data.remote.reddit_posts

import dev.xget.havasreddit.data.remote.api.ApiResponse
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto

interface RedditPostsRemoteDataSourceI {

    suspend fun getRedditPosts() : ApiResponse<List<RedditPostDto>>
}