package dev.xget.havasreddit.presentation.reddit_posts.home_screen.recycler_adapter

import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.commit451.coiltransformations.BlurTransformation
import dev.xget.havasreddit.R
import dev.xget.havasreddit.databinding.RedditPostItemBinding
import dev.xget.havasreddit.domain.model.RedditPost


class RedditPostAdapter(
    private val redditPosts: List<RedditPost> = emptyList(),
    private val clickListener: OnItemClickListener<RedditPost>? = null
) : RecyclerView.Adapter<RedditPostAdapter.RedditPostViewHolder>() {

    class RedditPostViewHolder(
        val binding: RedditPostItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reddit_post_item, parent, false)
        return RedditPostViewHolder(RedditPostItemBinding.bind(view))
    }

    override fun getItemCount(): Int = redditPosts.size

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {


        Log.d("RedditPostAdapter", "onBindViewHolder: ${redditPosts[position].title}")

        val currentPost = redditPosts[position]
        holder.binding.postTitleTextView.text = currentPost.title
        holder.binding.postAuthorTextView.text = currentPost.author
        holder.binding.upsCountTextView.text = currentPost.upVotes

        holder.binding.commentCountTextView.text = currentPost.numComments.toString()
        holder.binding.entirePostItem.setOnClickListener {
            clickListener?.itemClicked(currentPost)
        }
        if (!currentPost.hasMedia) {
            holder.binding.postImageCardView.visibility = View.GONE
        } else {
            holder.binding.postImageCardView.visibility = View.VISIBLE




            holder.binding.postImageView.load(currentPost.imageUrl) {
                placeholder(R.drawable.placeholder_image)
                listener(onError = { _, _ ->
                    holder.binding.postImageView.setImageResource(R.drawable.placeholder_image)
                    holder.binding.postImageView.scaleType = ImageView.ScaleType.FIT_XY
                    }
                )
                error(R.drawable.placeholder_image)
            }


            holder.binding.blurredImageView.load(currentPost.thumbnailUrl) {
                error(R.drawable.placeholder_image)
                transformations(BlurTransformation(holder.binding.root.context, 25f, 3f))
            }




        }

    }
}

