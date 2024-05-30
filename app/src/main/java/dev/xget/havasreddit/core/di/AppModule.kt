package dev.xget.havasreddit.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dev.xget.havasreddit.HavasRedditApp
import dev.xget.havasreddit.R
import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceImpl
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceI
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceImpl
import dev.xget.havasreddit.data.repository.reddit.RedditPostsRepositoryImpl
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


interface AppModuleI {
    val applicationContext: Context
    val redditPostsRemoteDataSource: RedditPostsRemoteDataSourceI
    val redditPostsRepository: RedditPostsRepository
    val sharedPreferences: SharedPreferences
    val redditPostsLocalDataSource: RedditPostsLocalDataSourceI
    val dispatcherIO: CoroutineDispatcher
}

class AppModule(
    private val application: HavasRedditApp,
) : AppModuleI {
    override val dispatcherIO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val applicationContext: Context by lazy {
        application.applicationContext
    }
    override val redditPostsRemoteDataSource: RedditPostsRemoteDataSourceI by lazy {
        RedditPostsRemoteDataSourceImpl(HavasRedditApp.networkModule.redditApiService)
    }
    override val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences(
            applicationContext.getString(R.string.app_name),
            Application.MODE_PRIVATE
        )
    }

    override val redditPostsLocalDataSource: RedditPostsLocalDataSourceI by lazy {
        RedditPostsLocalDataSourceImpl(sharedPreferences)
    }


    override val redditPostsRepository: RedditPostsRepository by lazy {
        RedditPostsRepositoryImpl(redditPostsRemoteDataSource, redditPostsLocalDataSource, dispatcherIO)
    }


}