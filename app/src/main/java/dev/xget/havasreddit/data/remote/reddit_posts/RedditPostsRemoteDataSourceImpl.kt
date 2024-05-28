package dev.xget.havasreddit.data.remote.reddit_posts

import dev.xget.havasreddit.data.remote.api.ApiResponse
import dev.xget.havasreddit.data.remote.api.RedditApiService
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto


class RedditPostsRemoteDataSourceImpl(
    private val redditApiService: RedditApiService
) : RedditPostsRemoteDataSourceI {

    override suspend fun getRedditPosts(): ApiResponse<List<RedditPostDto>> {
        try {
            val response = redditApiService.getRedditPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    val postsChildren : List<RedditPostDto> =
                        it.data?.children?.mapNotNull { it.postData }
                        .orEmpty()

                    return ApiResponse.ApiSuccess(postsChildren)
                }
            }
            return ApiResponse.ApiError("Error fetching data")
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.ApiError(e.message)
        }
    }
}