package dev.xget.havasreddit.data.local.reddit_posts

import android.content.SharedPreferences
import com.google.gson.Gson
import dev.xget.havasreddit.core.utils.CoreUtils.RedditPostSharedPrefKey
import dev.xget.havasreddit.domain.model.RedditPost


class RedditPostsLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences
) : RedditPostsLocalDataSourceI{

    override fun getSavedRedditPost(): RedditPost? {
        if (sharedPreferences.contains(RedditPostSharedPrefKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences.getString(RedditPostSharedPrefKey, ""), RedditPost::class.java)
        }
        return null
    }

    override fun saveRedditPost(post: RedditPost) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        val gson = Gson()
        val serializedObject = gson.toJson(post)
        sharedPreferencesEditor.putString(RedditPostSharedPrefKey, serializedObject)
        sharedPreferencesEditor.apply()
    }
}