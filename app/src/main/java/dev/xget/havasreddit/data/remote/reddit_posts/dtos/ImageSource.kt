package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName

data class ImageSource(
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)