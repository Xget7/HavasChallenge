package dev.xget.havasreddit

import dev.xget.havasreddit.data.remote.reddit_posts.dtos.PostImageDto
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.PostPreview
import dev.xget.havasreddit.data.remote.reddit_posts.dtos.RedditPostDto
import dev.xget.havasreddit.domain.model.RedditPost

object FakeDataObjects {


    val fakeDomainPost = RedditPost(
        author = "example_user_323",
        createdAt = 1653724800L, // Unix timestamp for example
        id = "post123abc",
        body = "This is a sample body text of a Reddit post. It contains some interesting content and details about the post.",
        numComments = "150",
        upVotes = "3.5k",
        thumbnailUrl = "https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fe4ffuw1ml73d1.jpeg",
        imageUrl = "https://i.redd.it/e4ffuw1ml73d1.jpeg",
        hasMedia = true,
        title = "Sample Title for a Reddit Post",
        onlyImage = false
    )

    val fakeDomainPostList = listOf(
        RedditPost(
            author = "user123",
            createdAt = 1653724800L,
            id = "post123abc",
            body = "",
            numComments = "125",
            upVotes = "2500",
            thumbnailUrl = "https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fe4ffuw1ml73d1.jpeg",
            imageUrl = "https://i.redd.it/e4ffuw1ml73d1.jpeg",
            hasMedia = true,
            title = "Check out this amazing sunset!",
            onlyImage = false
        ),
        RedditPost(
            author = "naturelover",
            createdAt = 1653710400L,
            id = "post456def",
            body = "",
            numComments = "76",
            upVotes = "1200",
            thumbnailUrl = "https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fe4ffuw1ml73d1.jpeg",
            imageUrl = "https://i.redd.it/e4ffuw1ml73d1.jpeg",
            hasMedia = true,
            title = "Beautiful Waterfall in the Forest",
            onlyImage = true
        ),
    )

    val fakeDtoList = listOf(
        RedditPostDto(
            author = "user123",
            authorFullname = "t2_abc123",
            contentCategories = listOf("Photography", "Nature"),
            created = 1653724800.0,
            createdUtc = 1653721200.0,
            domain = "i.redd.it",
            downs = 10,
            hidden = false,
            hideScore = false,
            id = "post123abc",
            isVideo = false,
            media = null,
            mediaOnly = false,
            name = "t3_post123abc",
            numComments = 125,
            numReports = null,
            permalink = "/r/pics/comments/post123abc/check_out_this_amazing_sunset/",
            preview = PostPreview(
                images = listOf(
                    PostImageDto(
                        id = "image1"
                    )
                ),
                enabled = true
            ),
            pwls = 0,
            quarantine = false,
            saved = false,
            score = 2500,
            selftext = "",
            stickied = false,
            subreddit = "pics",
            subredditNamePrefixed = "r/pics",
            thumbnailUrl = "https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fe4ffuw1ml73d1.jpeg",
            thumbnailHeight = 140,
            thumbnailWidth = 140,
            title = "Check out this amazing sunset!",
            ups = 2500,
            url = "https://i.redd.it/e4ffuw1ml73d1.jpeg"
        ),
        RedditPostDto(
            author = "naturelover",
            authorFullname = "t2_def456",
            contentCategories = listOf("Travel", "Adventure"),
            created = 1653710400.0,
            createdUtc = 1653706800.0,
            domain = "i.redd.it",
            downs = 5,
            hidden = false,
            hideScore = false,
            id = "post456def",
            isVideo = false,
            media = null,
            mediaOnly = true,
            name = "t3_post456def",
            numComments = 76,
            numReports = null,
            permalink = "/r/nature/comments/post456def/beautiful_waterfall_in_the_forest/",
            preview = PostPreview(
                images = listOf(
                    PostImageDto(
                        id = "image2"
                    )
                ),
                enabled = true
            ),
            pwls = 0,
            quarantine = false,
            saved = true,
            score = 1200,
            selftext = "",
            stickied = false,
            subreddit = "nature",
            subredditNamePrefixed = "r/nature",
            thumbnailUrl = "https://www.reddit.com/media?url=https%3A%2F%2Fi.redd.it%2Fe4ffuw1ml73d1.jpeg",
            thumbnailHeight = 140,
            thumbnailWidth = 140,
            title = "Beautiful Waterfall in the Forest",
            ups = 1200,
            url = "https://i.redd.it/e4ffuw1ml73d1.jpeg"
        ),
    )
}