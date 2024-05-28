package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class PostPreview(
    @SerializedName("enabled")
    val enabled: Boolean? = false,
    @SerializedName("images")
    val images: List<PostImageDto?>? = listOf()
)