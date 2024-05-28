package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName



data class RedditPostDto(

    @SerializedName("author")
    val author: String? = "",
    @SerializedName("author_fullname")
    val authorFullname: String? = "",
    @SerializedName("content_categories")
    val contentCategories: List<String>? = listOf(),
    @SerializedName("created")
    val created: Double? = 0.0,
    @SerializedName("created_utc")
    val createdUtc: Double? = 0.0,
    @SerializedName("domain")
    val domain: String? = "",
    @SerializedName("downs")
    val downs: Int? = 0,
    @SerializedName("edited")
    val edited: Double? = 0.0,
    @SerializedName("hidden")
    val hidden: Boolean? = false,
    @SerializedName("hide_score")
    val hideScore: Boolean? = false,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("is_video")
    val isVideo: Boolean? = false,
    @SerializedName("media")
    val media: Media? = Media(), //important
    @SerializedName("media_only")
    val mediaOnly: Boolean? = false,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("num_comments") // Important
    val numComments: Int? = 0,
    @SerializedName("num_reports")
    val numReports: Any? = Any(),
    @SerializedName("permalink")
    val permalink: String? = "",
    @SerializedName("preview")
    val preview: PostPreview? = PostPreview(), //important
    @SerializedName("pwls")
    val pwls: Int? = 0,
    @SerializedName("quarantine")
    val quarantine: Boolean? = false,
    @SerializedName("saved")
    val saved: Boolean? = false,
    @SerializedName("score")
    val score: Int? = 0,
    @SerializedName("selftext") //important post description (body)
    val selftext: String? = "",
    @SerializedName("stickied")
    val stickied: Boolean? = false,
    @SerializedName("subreddit")
    val subreddit: String? = "",
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int? = 0,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int? = 0,
    @SerializedName("title") //important
    val title: String? = "",
    @SerializedName("ups") //important
    val ups: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
)