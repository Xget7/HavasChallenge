package dev.xget.havasreddit.domain.model

//Model , part of the domain layer
data class RedditPost(
    val author: String = "",
    val createdAt: Long = 0,
    val id: String = "",
    val numComments: Int = 0,
    val score: Int = 0,
    val thumbnailUrl: String = "",
    val hasMedia : Boolean = false,
    val title: String = "",
    val url: String = "",
    val onlyImage : Boolean = false,
)
