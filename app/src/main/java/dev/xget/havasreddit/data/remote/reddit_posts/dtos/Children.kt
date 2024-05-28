package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class Children(
    @SerializedName("data")
    val postData: RedditPostDto? = RedditPostDto(),
    @SerializedName("kind")
    val kind: String? = ""
)