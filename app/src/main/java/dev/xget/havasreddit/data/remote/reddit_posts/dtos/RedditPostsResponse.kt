package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class RedditPostsResponse(
    @SerializedName("data")
    val data: PostsDto? = PostsDto(),
    @SerializedName("kind")
    val kind: String? = ""
)