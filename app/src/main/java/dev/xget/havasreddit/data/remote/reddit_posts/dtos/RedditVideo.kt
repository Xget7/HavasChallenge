package dev.xget.havasreddit.data.remote.reddit_posts.dtos


import com.google.gson.annotations.SerializedName


data class RedditVideo(
    @SerializedName("bitrate_kbps")
    val bitrateKbps: Int? = 0,
    @SerializedName("dash_url")
    val dashUrl: String? = "",
    @SerializedName("duration")
    val duration: Int? = 0,
    @SerializedName("fallback_url")
    val fallbackUrl: String? = "",
    @SerializedName("has_audio")
    val hasAudio: Boolean? = false,
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("hls_url")
    val hlsUrl: String? = "",
    @SerializedName("is_gif")
    val isGif: Boolean? = false,
    @SerializedName("scrubber_media_url")
    val scrubberMediaUrl: String? = "",
    @SerializedName("transcoding_status")
    val transcodingStatus: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)