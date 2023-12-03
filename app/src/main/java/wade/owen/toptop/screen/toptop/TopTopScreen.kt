package wade.owen.toptop.screen.toptop

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
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

//    val SCREEN_KEY = "TOP_TOP_SCREEN"
//    val topTopViewModel: TopTopViewModel = hiltViewModel(key = SCREEN_KEY)
//    val uiState = topTopViewModel.uiState.collectAsState()

    val pagerController: PagerState = rememberPagerState(
        pageCount = {
            15
        })

    val fling = PagerDefaults.flingBehavior(
        state = pagerController,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )

//    LaunchedEffect(pagerController) {
//        snapshotFlow {
//            pagerController.settledPage
//        }.collect { settledPage ->
//            Log.d("TTScreen", "settled " + settledPage.toString())
//            topTopViewModel.updateLinkVideo(settledPage)
//        }
//    }

//    LaunchedEffect(pagerController) {
//        snapshotFlow { pagerController.currentPage }.collect { currentPage ->
//            Log.d("TTScreen", "current " + currentPage.toString())
//            topTopViewModel.loading()
//        }
//    }

    VerticalPager(state = pagerController, flingBehavior = fling) {
        val videoViewModel: VideoViewModel = hiltViewModel(key = it.toString())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            VideoDetailScreen(videoId = it, videoViewModel = videoViewModel)
        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.Black),
//            contentAlignment = Alignment.Center,
//        ) {
//            Log.d("TTScreen", uiState.value.isLoading.toString())
//            ComposeVideoPlayer(
//                modifier = Modifier,
//                player = videoViewModel.videoPlayer,
//                urlVideo = uiState.value.currentVideoUrl
//            )
//        }
    }
}