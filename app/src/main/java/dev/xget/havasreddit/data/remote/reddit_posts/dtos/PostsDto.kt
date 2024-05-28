package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class PostsDto(
    @SerializedName("children")
    val children: List<Children>? = listOf(),
)