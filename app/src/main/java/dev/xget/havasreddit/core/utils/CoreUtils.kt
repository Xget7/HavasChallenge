package dev.xget.havasreddit.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dev.xget.havasreddit.domain.model.RedditPost

object CoreUtils {

    const val RedditPostSharedPrefKey = "RedditPostSharedPrefKey"

    fun getFormattedUpVotes(upVotes: String): String {
        return when {
            upVotes.length > 6 -> {
                val upVotesInMillions = upVotes.substring(0, upVotes.length - 6)
                "$upVotesInMillions m"
            }
            upVotes.length > 3 -> {
                val upVotesInThousands = upVotes.substring(0, upVotes.length - 3)
                "$upVotesInThousands k"
            }
            else -> upVotes
        }
    }
    fun getFormattedComments(comments: Int): String {
        val numComments = comments.toString()
        return when {
            numComments.length > 6 -> {
                val commentsInMillions = numComments.substring(0, numComments.length - 6)
                "$commentsInMillions m"
            }
            numComments.length > 3 -> {
                val commentsInThousands = numComments.substring(0, numComments.length - 3)
                "$commentsInThousands k"
            }
            else -> numComments
        }
    }
    val imageExtensionsForDownload = listOf("jpeg", "png", "jpg", "webp")

}

object NetworkUtils {

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}

object MockClasses {
    val redditPostsDomain = listOf(
        RedditPost(
            author = "author",
            createdAt = 0,
            id = "id",
            numComments = "0",
            upVotes = "upVotes",
            thumbnailUrl = "thumbnailUrl",
            imageUrl = "imageUrl",
            hasMedia = false,
            title = "title",
            onlyImage = false
        )
    )

}