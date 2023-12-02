package wade.owen.toptop.screen.toptop

import android.util.Log
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C.WAKE_MODE_LOCAL
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import wade.owen.toptop.data.repositories.VideoRepository
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class VideoViewModel @OptIn(UnstableApi::class) @Inject constructor(
    val videoPlayer: ExoPlayer,
    val videoUrl: String,
    private val videoRepository: VideoRepository,
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<VideoUiState>(VideoUiState.Default) /// mutable Livedata
    val uiState: StateFlow<VideoUiState> get() = _uiState /// Live Data

    val testVideo = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    init {
        videoPlayer.repeatMode = REPEAT_MODE_ALL
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()
        viewModelScope.launch {
            try {
                val response = videoRepository.getListVideo()
                if (response != null) {
                    val listVideo = response.videos
                    val video = listVideo[Random.nextInt(0, 14)]
                    val urlVideo = video.video_files.first().link  // files[2] for 1280 x 960

                    val mediaItem = MediaItem.fromUri(urlVideo)

                    videoPlayer.setMediaItem(mediaItem)
                    videoPlayer.play()
                }
            } catch (e: Exception) {
                Log.e("VIDEO_VM", "Disconnected!!!!!")
            }
        }
    }

    fun handleAction(action: VideoAction) {
        when (action) {
            is VideoAction.LoadData -> {
                val videoId = action.idVideo
            }

            is VideoAction.ToggleVideo -> {

            }
        }
    }

    private fun loadVideo() {
        _uiState.value = VideoUiState.Loading
        viewModelScope.launch {
            val listVideo = videoRepository.getListVideo()
            print(listVideo)
            _uiState.value = VideoUiState.Success
        }
    }

    private fun playVideo() {

    }
}

sealed interface VideoUiState {
    object Default : VideoUiState
    object Loading : VideoUiState
    object Success : VideoUiState
    data class Error(val msg: String) : VideoUiState
}

sealed class VideoAction {
    data class LoadData(val idVideo: Int) : VideoAction()
    object ToggleVideo : VideoAction()
}