package dev.xget.havasreddit.data.repository.reddit

import android.content.SharedPreferences
import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceI
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import org.junit.Assert.*

class FakeAndroidRedditPostsRepositoryTest(
    val postsRemoteDataSourceI: RedditPostsRemoteDataSourceI,
    val localDataSourceI: RedditPostsLocalDataSourceI
) : RedditPostsRepository {

    override suspend fun getRedditPosts(): List<RedditPost> {
        return postsRemoteDataSourceI.getRedditPosts().data?.map { it.asDomain() } ?: throw Exception("No data")
    }

    override fun saveRedditPost(redditPost: RedditPost) {
        localDataSourceI.saveRedditPost(redditPost)
    }

    override fun getSavedRedditPost(): RedditPost? {
        return localDataSourceI.getSavedRedditPost()
    }

}