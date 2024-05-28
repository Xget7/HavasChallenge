package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName


data class PostImageDto(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("resolutions")
    val resolutions: List<Resolution?>? = listOf(),
    @SerializedName("source")
    val source: ImageSource? = ImageSource(),
)