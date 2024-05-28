package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import kotlinx.serialization.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LinkFlairRichtext(
    @SerializedName("e")
    val e: String? = "",
    @SerializedName("t")
    val t: String? = ""
)