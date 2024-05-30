package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import dev.xget.havasreddit.HavasRedditApp
import dev.xget.havasreddit.R
import dev.xget.havasreddit.databinding.FragmentPostDetailBinding
import dev.xget.havasreddit.domain.model.RedditPost
import dev.xget.havasreddit.presentation.reddit_posts.home_screen.HomeScreenViewModel
import dev.xget.havasreddit.presentation.utils.viewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                val viewModel = viewModel<PostDetailViewModel>(
                    factory =
                    viewModelFactory {
                        PostDetailViewModel(HavasRedditApp.appModule.redditPostsRepository)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                PostDetailsScreen(viewModel = viewModel)
                BackHandler {
                    // Handle back press
                    onBackPress()
                    Log.d("PostDetailFragment", "onCreateView: BackHandler")
                }
            }

        }
    }

    fun onBackPress() {
        // Handle back press
        findNavController().navigateUp()
    }

    fun parseSavedPost(redditPost: RedditPost) {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}