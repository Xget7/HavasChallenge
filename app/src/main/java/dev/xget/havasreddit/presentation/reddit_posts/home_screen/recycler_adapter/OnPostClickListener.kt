package dev.xget.havasreddit.presentation.reddit_posts.home_screen.recycler_adapter

// Interface for handling click events on the recycler view items
interface OnPostClickListener {
    fun itemClicked(position:Int)
}