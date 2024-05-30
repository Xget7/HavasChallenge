package dev.xget.havasreddit.presentation.reddit_posts.home_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.xget.havasreddit.FakeDataObjects.fakeDomainPostList
import dev.xget.havasreddit.data.repository.reddit.FakeRedditPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private lateinit var homeScreenViewModel: HomeScreenViewModel

    private lateinit var fakeRedditPostsRepository: FakeRedditPostRepository

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    //This rule makes Android Archiotecture components in background to work synchronously
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setupViewModel() {
        Dispatchers.setMain(dispatcher)
        fakeRedditPostsRepository = FakeRedditPostRepository(fakeDomainPostList)

        homeScreenViewModel = HomeScreenViewModel(fakeRedditPostsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getRedditPostUpdatesLiveData() = runTest(dispatcher) {
        val redditPosts = homeScreenViewModel._redditPosts.value
        homeScreenViewModel.getRedditPosts()
        advanceUntilIdle()
        assertEquals(redditPosts?.size, fakeDomainPostList.size)
    }
    @Test
    fun onNavigatedToDetailsScreenShouldSetNavigateToDetailsScreenLiveDataToFalse() = runTest(dispatcher){
        homeScreenViewModel.onNavigatedToDetailsScreen()
        advanceUntilIdle()
        assertFalse(homeScreenViewModel.navigateToDetailsScreenLiveData.value!!)
    }
    @Test
    fun onPostClickListenerCallbackShouldSaveRedditPostAndSetNavigateToDetailsScreenLiveDataToTrue() = runTest(dispatcher){
        val redditPost = fakeDomainPostList[0]
        homeScreenViewModel.onPostClickListenerCallback.itemClicked(redditPost)
        advanceUntilIdle()
        assertEquals(redditPost, fakeRedditPostsRepository.getSavedRedditPost())
        assertTrue(homeScreenViewModel.navigateToDetailsScreenLiveData.value!!)
    }
}