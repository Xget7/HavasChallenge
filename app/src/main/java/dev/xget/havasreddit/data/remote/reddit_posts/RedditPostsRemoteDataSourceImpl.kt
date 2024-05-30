package dev.xget.havasreddit.data.remote.reddit_posts

import android.util.Log
import dev.xget.havasreddit.data.remote.api.ApiResponse
import dev.xget.havasreddit.data.remote.api.RedditApiService
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto


class RedditPostsRemoteDataSourceImpl(
    private val redditApiService: RedditApiService
) : RedditPostsRemoteDataSourceI {

    override suspend fun getRedditPosts(): ApiResponse<List<RedditPostDto>> {
        try {
            val response = redditApiService.getRedditPosts()
            Log.d("RedditPostsRemoteDataSourceImpl", "response: $response")
            if (response.isSuccessful) {
                response.body()?.let {
                    val postsChildren : List<RedditPostDto> =
                        it.data?.children?.mapNotNull { it.postData }
                        .orEmpty()

                    return ApiResponse.Success(postsChildren)
                }
            }
            return ApiResponse.Error("Error fetching data")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("RedditPostsRemoteDataSourceImpl", "Error fetching data: ${e.message}")
            return ApiResponse.Error(e.message)
        }
    }
}