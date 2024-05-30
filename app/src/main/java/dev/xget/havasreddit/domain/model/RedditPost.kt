package dev.xget.havasreddit.domain.model

//Model , part of the domain layer
data class RedditPost(
    val author: String = "",
    val createdAt: Long = 0,
    val id: String = "",
    val body: String = "",
    val numComments: String = "",
    val upVotes: String = "",
    val thumbnailUrl: String = "",
    val imageUrl: String = "",
    val hasMedia: Boolean = false,
    val title: String = "",
    val onlyImage: Boolean = false,
) {

}
