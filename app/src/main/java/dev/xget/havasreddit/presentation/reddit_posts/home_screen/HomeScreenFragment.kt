package dev.xget.havasreddit.presentation.reddit_posts.home_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.xget.havasreddit.HavasRedditApp
import dev.xget.havasreddit.R
import dev.xget.havasreddit.core.utils.MockClasses.redditPostsDomain
import dev.xget.havasreddit.databinding.FragmentHomeScreenBinding
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.presentation.reddit_posts.home_screen.recycler_adapter.RedditPostAdapter
import dev.xget.havasreddit.presentation.utils.viewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: HomeScreenViewModel

    var binding: FragmentHomeScreenBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel =
            ViewModelProvider(this, dev.xget.havasreddit.presentation.utils.viewModelFactory {
                HomeScreenViewModel(HavasRedditApp.appModule.redditPostsRepository)
            })[HomeScreenViewModel::class.java]

        lifecycleScope.launch {


            viewModel.navigateToDetailsScreenLiveData.observe(this@HomeScreenFragment) { navigate ->
                if (navigate) {
                    // Navigate to details screen
                    Log.d("HomeScreenFragment", "onCreate: Navigate to details screen")
                    findNavController().navigate(R.id.action_homeScreenFragment_to_postDetailFragment)
                    viewModel.onNavigatedToDetailsScreen()
                }
            }
        }
        Log.d("HomeScreenFragment", "onCreate: ")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.redditPosts.observe(viewLifecycleOwner) {
            Log.d("HomeScreenFragment", "onCreate: ${it.size}")
            updateRecyclerView(it)
        }
    }

    private fun updateRecyclerView(redditPosts: List<RedditPost>) {
        val adapter = RedditPostAdapter(redditPosts, viewModel.onPostClickListenerCallback)
        binding?.rvView?.adapter = adapter
        Log.d("HomeScreenFragment", "updateRecyclerView: ${redditPosts.size} ${adapter.itemCount}")
    }
}


