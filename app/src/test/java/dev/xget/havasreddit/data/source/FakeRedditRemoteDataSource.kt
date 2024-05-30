package dev.xget.havasreddit.data.source

import dev.xget.havasreddit.data.remote.api.ApiResponse
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceI
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto

class FakeRedditRemoteDataSource(val posts: List<RedditPostDto>?) : RedditPostsRemoteDataSourceI {


    override suspend fun getRedditPosts(): ApiResponse<List<RedditPostDto>> {
        return if (posts != null) {
            ApiResponse.Success(posts)
        } else {
            ApiResponse.Error("Error fetching posts from reddit")
        }
    }
}