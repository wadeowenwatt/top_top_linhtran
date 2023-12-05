package wade.owen.toptop.screen.toptop

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import wade.owen.toptop.screen.toptop.video.VideoDetailScreen
import wade.owen.toptop.screen.toptop.video.VideoViewModel

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun TopTopScreen() {

    val topTopViewModel: TopTopViewModel = hiltViewModel<TopTopViewModel>(key = "TopTopVM")
    val uiState = topTopViewModel.uiState.collectAsState()

    val pagerState = topTopViewModel.pagerState

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10),
//        lowVelocityAnimationSpec = tween(
//            easing = LinearEasing, durationMillis = 300
//        )
    )

    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.settledPage
        }.collect { settledPage ->
            Log.d("TTScreen", "settled $settledPage")
            if (topTopViewModel.currentPage != settledPage) {
                topTopViewModel.swipeChangeVideo(settledPage)
            }
        }
    }

    VerticalPager(
        state = pagerState,
        flingBehavior = fling,
        beyondBoundsPageCount = 0,
    ) { page ->
//        val videoViewModel = hiltViewModel<VideoViewModel>(key = page.toString())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            VideoDetailScreen(
                pagerState = pagerState,
                uiState.value,
//                videoViewModel,
                topTopViewModel.videoPlayer
            )
        }

//        DisposableEffect(page) {
//            onDispose {
////                Log.d("linhtn1","Page $it is no longer displayed")
//            }
//        }
    }
}