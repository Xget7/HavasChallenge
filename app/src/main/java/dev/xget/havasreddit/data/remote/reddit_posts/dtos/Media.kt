package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import kotlinx.serialization.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    @SerializedName("reddit_video")
    val redditVideo: RedditVideo? = RedditVideo()
)