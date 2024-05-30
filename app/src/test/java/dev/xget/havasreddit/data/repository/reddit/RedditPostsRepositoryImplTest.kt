package dev.xget.havasreddit.data.repository.reddit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.xget.havasreddit.FakeDataObjects.fakeDomainPost
import dev.xget.havasreddit.FakeDataObjects.fakeDomainPostList
import dev.xget.havasreddit.FakeDataObjects.fakeDtoList
import dev.xget.havasreddit.data.local.reddit_posts.RedditPostsLocalDataSourceI
import dev.xget.havasreddit.data.remote.reddit_posts.RedditPostsRemoteDataSourceI
import dev.xget.havasreddit.data.source.FakeRedditLocalDataSource
import dev.xget.havasreddit.data.source.FakeRedditRemoteDataSource
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.invoke
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

//Unit tests for RedditPostsRepositoryImpl
class RedditPostsRepositoryImplTest {

    private lateinit var postRemoteDataSource: RedditPostsRemoteDataSourceI
    private lateinit var postLocalDataSource: RedditPostsLocalDataSourceI

    private lateinit var redditPostsRepository: RedditPostsRepository
    private val testDispatcher = StandardTestDispatcher(scheduler = TestCoroutineScheduler())


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUpRepository() {
        postRemoteDataSource = FakeRedditRemoteDataSource(fakeDtoList)
        postLocalDataSource = FakeRedditLocalDataSource(fakeDomainPost)
        Dispatchers.setMain(testDispatcher)
        redditPostsRepository =
            RedditPostsRepositoryImpl(postRemoteDataSource, postLocalDataSource, testDispatcher)
    }


    @Test
    fun getRedditPostsShouldReturnAListOfRedditPosts(): Unit = runTest {
        val result = redditPostsRepository.getRedditPosts().map { it.id }.sorted()

        val fakeDomainPostList = fakeDomainPostList.map { it.id }.sorted()
        assertEquals(fakeDomainPostList, result)
    }

    @Test
    fun saveRedditPostShouldSaveRedditPost(): Unit = runTest {
        val post = fakeDomainPost
        redditPostsRepository.saveRedditPost(post)
        val savedPost = redditPostsRepository.getSavedRedditPost()
        assertEquals(post, savedPost)
    }
}