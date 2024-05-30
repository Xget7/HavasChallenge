package dev.xget.havasreddit.presentation.reddit_posts.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.text.buildSpannedString
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.imageLoader
import dev.xget.havasreddit.R
import dev.xget.havasreddit.domain.model.RedditPost

@Composable
fun PostDetailsScreen(
    viewModel: PostDetailViewModel
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    PostDetailsScreenContent(state = state)

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostDetailsScreenContent(
    state: State<PostDetailsScreenUiState>,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            if (state.value.isLoading) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.value.redditPost != null) {
                PostItemDetailComponent(redditPost = state.value.redditPost!!)
            }
        }
    }

    LaunchedEffect(key1 = state.value.error, block = {
        if (state.value.error != null) {
            // Show error dialog
            snackbarHostState.showSnackbar(
                message = state.value.error!!,
                actionLabel = "Dismiss"
            )
        }
    })


}

@Composable
fun PostItemDetailComponent(redditPost: RedditPost) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "By " + redditPost.author, color = Color.Gray, maxLines = 1)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = redditPost.title,
            color = Color.Black,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (redditPost.hasMedia) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                ) {
                    Box() {
                        AsyncImage(
                            model = redditPost.imageUrl,
                            contentDescription = "Post Image",
                            imageLoader = context.imageLoader,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .onSizeChanged { size = it }
                                .pointerInput(Unit) {
                                    detectTransformGestures { centroid, pan, zoom, rotation ->
                                        scale = maxOf(1f, minOf(scale * zoom, 5f))
                                        val maxX = (size.width * (scale - 1)) / 2
                                        val minX = -maxX
                                        offsetX = maxOf(minX, minOf(maxX, offsetX + pan.x))
                                        val maxY = (size.height * (scale - 1)) / 2
                                        val minY = -maxY
                                        offsetY = maxOf(minY, minOf(maxY, offsetY + pan.y))

                                    }
                                }
                                .graphicsLayer(
                                    // adding some zoom limits (min 50%, max 200%)
                                    scaleX = scale,
                                    scaleY = scale,
                                    translationX = offsetX,
                                    translationY = offsetY
                                ).zIndex(1f),
                            clipToBounds = true
                        )

                    }

                }

            }
        }
        Text(text = redditPost.body, color = Color.Black, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(5.dp))
        PostReactionsBar(redditPost = redditPost)
    }
}

@Composable
fun PostReactionsBar(redditPost: RedditPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.up_arrow_ic),
            contentDescription = "Up Vote",
            modifier = Modifier.size(15.dp)
        )
        Text(
            text = redditPost.upVotes,
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(painter = painterResource(id = R.drawable.comment_ic), contentDescription = "Comment")
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                    append(redditPost.numComments)
                }
                withStyle(SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                    append(" Comments")
                }
            },
            modifier = Modifier.padding(start = 5.dp),
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun PostDetailsScreenPreview() {
    PostDetailsScreenContent(
        state = mutableStateOf(
            PostDetailsScreenUiState(
                redditPost = RedditPost(
                    author = "Similar_Rutabaga_593",
                    createdAt = 1716966331,
                    id = "1d362r0",
                    numComments = "2 K",
                    upVotes = "3 K",
                    thumbnailUrl = "image",
                    imageUrl = "https://i.redd.it/atpt8yndeb3d1.jpeg",
                    hasMedia = true,
                    title = "Oof.",
                    onlyImage = false
                )
            )
        )
    )
}