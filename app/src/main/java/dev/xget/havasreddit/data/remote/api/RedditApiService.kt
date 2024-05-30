package dev.xget.havasreddit.data.remote.api

import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RedditApiService {

    @GET(".json")
    suspend fun getRedditPosts() : Response<RedditPostsResponse>

}