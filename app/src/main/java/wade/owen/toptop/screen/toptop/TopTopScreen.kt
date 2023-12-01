package wade.owen.toptop.screen.toptop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import wade.owen.toptop.compose.ComposeVideoPlayer

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun TopTopScreen() {
//    val topTopViewModel = viewModel(modelClass = TopTopViewModel::class.java)
//    val state by topTopViewModel.uiState.collectAsState()
    val SCREEN_KEY = "TOP_TOP_SCREEN"
    val topTopViewModel: TopTopViewModel = hiltViewModel(key = SCREEN_KEY)


    VerticalPager(state = topTopViewModel.pagerController) {page ->
        val videoViewModel: VideoViewModel = hiltViewModel(key = page.toString())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.CenterStart,
        ) {
            ComposeVideoPlayer(modifier = Modifier, player = videoViewModel.videoPlayer)
        }
    }

}