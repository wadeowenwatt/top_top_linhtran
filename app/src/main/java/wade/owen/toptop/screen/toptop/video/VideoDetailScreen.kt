package wade.owen.toptop.screen.toptop.video

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import wade.owen.toptop.compose.ComposeVideoPlayer

@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel, videoId: Int) {
    val uiState = videoViewModel.uiState.collectAsState()
    VideoDetailScreen(
        uiState = uiState.value,
        player = videoViewModel.videoPlayer,
    ) { videoAction ->
        videoViewModel.handleAction(videoAction)
    }
}

@Composable
fun VideoDetailScreen(
    uiState: VideoUiState,
    player: Player,
    handleAction: (VideoAction) -> Unit,
) {
    when (uiState) {
        is VideoUiState.Loading -> {
            Text(text = "Loading...", color = Color.White)
        }

        is VideoUiState.Success -> {
            Log.d("linhtn1", "test first url " + uiState.urlVideo)
            VideoDetailScreen(player = player, handleAction = handleAction)
        }

        else -> {}
    }
}

@Composable
fun VideoDetailScreen(
    player: Player,
    handleAction: (VideoAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                handleAction(VideoAction.ToggleVideo)
            },
        contentAlignment = Alignment.Center
    ) {
        ComposeVideoPlayer(modifier = Modifier, player = player)
    }
}