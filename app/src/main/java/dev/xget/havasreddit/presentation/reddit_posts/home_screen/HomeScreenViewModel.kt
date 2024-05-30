package dev.xget.havasreddit.presentation.reddit_posts.home_screen

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.domain.repository.reddit.RedditPostsRepository
import dev.xget.havasreddit.presentation.reddit_posts.home_screen.recycler_adapter.OnItemClickListener
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val redditPostsRepository: RedditPostsRepository
) : ViewModel() {

    private val navigateToDetailsScreen = MutableLiveData(false)
    val navigateToDetailsScreenLiveData = navigateToDetailsScreen
    val _redditPosts = MutableLiveData(emptyList<RedditPost>())
    val redditPosts = _redditPosts
    val errorState = MutableLiveData(Pair(false, ""))

    init {
        getRedditPosts()
    }

    fun getRedditPosts() {
        viewModelScope.launch {
            try {
                redditPostsRepository.getRedditPosts().let {
                    Log.d("HomeScreenViewModel", "getRedditPosts: $it")
                    _redditPosts.postValue(it)
                }
            }catch (e: Exception){
                Log.e("HomeScreenViewModel", "getRedditPosts: ${e.message}", e)
                errorState.postValue(Pair(true, e.message ?: "Error fetching data from server"))
            }
        }
    }

    fun onNavigatedToDetailsScreen() {
        navigateToDetailsScreen.postValue(false)
    }

    fun clearError() {
        errorState.postValue(Pair(false, ""))
    }

    val onPostClickListenerCallback = object : OnItemClickListener<RedditPost> {
        override fun itemClicked(item: RedditPost) {
            // Handle click event
            Log.d("HomeScreenViewModel", "itemClicked: ${item.id}")
            redditPostsRepository.saveRedditPost(item)
            navigateToDetailsScreen.postValue(true)

        }
    }


}