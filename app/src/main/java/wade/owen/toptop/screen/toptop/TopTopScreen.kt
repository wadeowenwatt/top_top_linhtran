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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import wade.owen.toptop.screen.toptop.video.ComposeVideoPlayer

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun TopTopScreen() {
//    val topTopViewModel = viewModel(modelClass = TopTopViewModel::class.java)
//    val state by topTopViewModel.uiState.collectAsState()
    val SCREEN_KEY = "TOP_TOP_SCREEN"
    val topTopViewModel: TopTopViewModel = hiltViewModel(key = SCREEN_KEY)
//    val pagerController: PagerState = rememberPagerState(
//        pageCount = {
//            Log.d("TTScreen", topTopViewModel.uiState.value.listVideoData?.videos?.size.toString())
//            topTopViewModel.uiState.value.listVideoData?.videos?.size ?: 1
//        },
//    )
    val pagerController: PagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
//        Log.d("TTScreen",
//            topTopViewModel.uiState.value.listVideoData?.videos?.size.toString()
//        )
        15
    }

    val fling = PagerDefaults.flingBehavior(
        state = pagerController,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )

    LaunchedEffect(pagerController) {
        snapshotFlow {
            pagerController.settledPage
        }.collect { settledPage ->
            Log.d("TTScreen", settledPage.toString())
            topTopViewModel.changeVideo(settledPage)

        }
    }

    VerticalPager(state = pagerController, flingBehavior = fling) {
//        val videoViewModel: VideoViewModel = hiltViewModel(key = page.toString())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.CenterStart,
        ) {
            ComposeVideoPlayer(modifier = Modifier, player = topTopViewModel.videoPlayer)
        }
    }

}