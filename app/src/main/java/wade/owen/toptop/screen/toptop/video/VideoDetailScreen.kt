package wade.owen.toptop.screen.toptop.video

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import wade.owen.toptop.compose.ComposeVideoPlayer
import wade.owen.toptop.screen.toptop.TopTopUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoDetailScreen(
    pagerState: PagerState,
    uiState: TopTopUiState,
//    videoViewModel: VideoViewModel,
    player: Player,
) {

//    val uiState = videoViewModel.uiState.collectAsState()
//    videoViewModel.updateState(urlVideo = urlVideo)

    Log.d("linhtn1", "Page ${pagerState.currentPage}")
    VideoDetailScreen(
        uiState = uiState,
        player = player,
    ) { videoAction ->
//        videoViewModel.handleAction(videoAction)
    }
}

@Composable
fun VideoDetailScreen(
    uiState: TopTopUiState,
    player: Player,
    handleAction: (VideoAction) -> Unit,
) {
    if (uiState.isLoading) {
        Log.d("linhtn1", "Loading")
        Text(text = "Loading...", color = Color.White)
    } else {
        Log.d("linhtn1", "test first url " + uiState.currentVideoUrl)
        VideoDetailScreen(player = player, handleAction = handleAction)
    }

//    when (uiState) {
//        is VideoUiState.Loading -> {
//            Log.d("linhtn1", "Loading")
//            Text(text = "Loading...", color = Color.White)
//        }
//
//        is VideoUiState.Success -> {
//            Log.d("linhtn1", "test first url " + uiState.urlVideo)
//            VideoDetailScreen(player = player, handleAction = handleAction)
//        }
//
//        else -> {}
//    }
}

@Composable
fun VideoDetailScreen(
    player: Player,
    handleAction: (VideoAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {
                handleAction(VideoAction.ToggleVideo)
            }),
        contentAlignment = Alignment.Center
    ) {
        ComposeVideoPlayer(modifier = Modifier, player = player)
    }
}