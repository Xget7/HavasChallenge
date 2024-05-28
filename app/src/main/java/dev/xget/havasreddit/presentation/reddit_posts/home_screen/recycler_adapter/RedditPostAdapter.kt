package dev.xget.havasreddit.presentation.reddit_posts.home_screen.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.xget.havasreddit.databinding.RedditPostItemBinding
import dev.xget.havasreddit.domain.model.RedditPost

class RedditPostAdapter (
    private val redditPosts: List<RedditPost>,
    private val clickListener: OnPostClickListener
) : RecyclerView.Adapter<RedditPostAdapter.RedditPostViewHolder>() {

    class RedditPostViewHolder(val binding: RedditPostItemBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RedditPostViewHolder(RedditPostItemBinding.inflate(inflater,parent,false ))
    }

    override fun getItemCount(): Int {
        return redditPosts.size
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.binding.postImageView
        holder.binding.entirePostItem.setOnClickListener {
            clickListener.itemClicked(position)
        }
    }
}

