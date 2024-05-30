package dev.xget.havasreddit.presentation.reddit_posts.home_screen

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dev.xget.havasreddit.HavasRedditApp
import dev.xget.havasreddit.R
import dev.xget.havasreddit.core.utils.MockClasses.redditPostsDomain
import dev.xget.havasreddit.data.repository.reddit.FakeAndroidRedditPostsRepositoryTest
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import dev.xget.havasreddit.FakeDataObjects
import dev.xget.havasreddit.FakeDataObjects.fakeDomainPostList
import dev.xget.havasreddit.core.utils.MockClasses
import dev.xget.havasreddit.data.sources.FakeAndroidRedditLocalDataSource
import dev.xget.havasreddit.data.sources.FakeAndroidRedditRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
@MediumTest //"medium run-time" integration test
@RunWith(AndroidJUnit4::class)
class HomeScreenFragmentTest {

    private lateinit var homeScreenViewModel: HomeScreenViewModel
    private lateinit var repository: RedditPostsRepository
    private lateinit var instrumentationContext: Context

    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    //This rule makes Android Archiotecture components in background to work synchronously
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        instrumentationContext =
            androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().context
        /*
         getRedditPosts: No data java.lang.Exception: No data
            at dev.xget.havasreddit.data.repository.reddit.FakeAndroidRedditPostsRepositoryTest.getRedditPosts
         */
        repository = FakeAndroidRedditPostsRepositoryTest(
            FakeAndroidRedditRemoteDataSource(FakeDataObjects.fakeDtoList),
            FakeAndroidRedditLocalDataSource(fakeDomainPostList[0])
        )
        HavasRedditApp.appModule.redditPostsRepository = repository
        homeScreenViewModel = HomeScreenViewModel(repository)
    }

    @After
    fun tearDown() = runTest {
        Dispatchers.resetMain()
    }


    //Later can use expresso to test navigation when click on a post
    @Test
    fun onRedditPostsAreNotNull() = runTest(dispatcher) {
        //Given
        launchFragmentInContainer<HomeScreenFragment>(null, R.style.Theme_HavasReddit)

        //When
        homeScreenViewModel.getRedditPosts()
        advanceUntilIdle()
        //Then

        assertNotNull(homeScreenViewModel.redditPosts.value)
    }

    @Test
    fun onRedditPostAreDisplayed() = runTest {

        //Given
        //Viewmodel Init funciton calls getRedditPosts
        //When
        launchFragmentInContainer<HomeScreenFragment>(null, R.style.Theme_HavasReddit)


        Log.d("redditPosts from testing:", "${homeScreenViewModel.redditPosts.value}")
        //Then
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_view))
//            .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
//                hasDescendant(withText("TITLE1")), click()))
    }
}