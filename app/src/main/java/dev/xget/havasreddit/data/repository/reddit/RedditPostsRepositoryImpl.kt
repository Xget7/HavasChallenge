package dev.xget.havasreddit.data.repository.reddit

import android.util.Log
import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.data.remote.api.ApiResponse
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceI
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RedditPostsRepositoryImpl(
    private val redditPostsRemoteDataSource: RedditPostsRemoteDataSourceI,
    private val redditPostsLocalDataSource: RedditPostsLocalDataSourceI,
    private val dispatcher: CoroutineDispatcher
) : RedditPostsRepository {


    override suspend fun getRedditPosts(): List<RedditPost> {
        return withContext(dispatcher) {
            try {
                when (val response = redditPostsRemoteDataSource.getRedditPosts()) {
                    is ApiResponse.Success -> {
                        Log.d("RedditPostsRepositoryImpl", "response: ${response.data}")
                        return@withContext response.data?.map { it.asDomain() }?.sortedBy { it.createdAt }
                            ?: emptyList()
                    }

                    is ApiResponse.Error -> {
                        throw Exception("Error fetching data")
                    }

                    is ApiResponse.Loading -> {}
                }
                return@withContext emptyList()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override fun saveRedditPost(redditPost: RedditPost) {
        redditPostsLocalDataSource.saveRedditPost(redditPost)
    }

    override fun getSavedRedditPost(): RedditPost? {
        return redditPostsLocalDataSource.getSavedRedditPost()
    }
}