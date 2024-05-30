package dev.xget.havasreddit.presentation.reddit_posts.home_screen

import android.content.Context
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dev.xget.havasreddit.HavasRedditApp
import dev.xget.havasreddit.R
import dev.xget.havasreddit.data.repository.reddit.FakeAndroidRedditPostsRepositoryTest
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest //"medium run-time" integration test
@RunWith(AndroidJUnit4::class)
class HomeScreenFragmentTest {

    lateinit var homeScreenViewModel: HomeScreenViewModel
    lateinit var repository: RedditPostsRepository
    lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        instrumentationContext =
            androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().context
        repository = FakeAndroidRedditPostsRepositoryTest(
            HavasRedditApp.appModule.redditPostsRemoteDataSource,
            HavasRedditApp.appModule.redditPostsLocalDataSource
        )
        homeScreenViewModel = HomeScreenViewModel(repository)
    }

    @After
    fun tearDown() = runTest {

    }


    @Test
    fun test() {
        //Given

        //When


        //Then
        launchFragmentInContainer<HomeScreenFragment>(null, R.style.Theme_HavasReddit)
    }
}