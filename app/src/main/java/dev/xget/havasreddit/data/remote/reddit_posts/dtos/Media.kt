package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class Media(
    @SerializedName("reddit_video")
    val redditVideo: RedditVideo? = RedditVideo()
)