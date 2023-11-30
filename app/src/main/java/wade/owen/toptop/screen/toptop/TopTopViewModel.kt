package wade.owen.toptop.screen.toptop

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C.WAKE_MODE_LOCAL
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import wade.owen.toptop.R
import wade.owen.toptop.data.repositories.VideoRepository
import javax.inject.Inject

@HiltViewModel
class TopTopViewModel @OptIn(UnstableApi::class) @Inject constructor(
    val videoPlayer: ExoPlayer,
    private val videoRepository: VideoRepository,
) : ViewModel() {
    private var _uiState =
        MutableStateFlow<TopTopUiState>(TopTopUiState.Default) /// mutable Livedata
    val uiState: StateFlow<TopTopUiState> get() = _uiState /// Live Data

    init {
        videoPlayer.repeatMode = REPEAT_MODE_ALL
        videoPlayer.setWakeMode(WAKE_MODE_LOCAL)
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()
        viewModelScope.launch {
            val response = videoRepository.getListVideo()
            val listVideo = response.videos
            val video = listVideo.first()
            val urlVideo = video.video_files.first().link;

            val mediaItem = MediaItem.fromUri(urlVideo)

            videoPlayer.setMediaItem(mediaItem)


            videoPlayer.play()
        }
//        val uri = RawResourceDataSource.buildRawResourceUri(R.raw.test)
//        val mediaItem = MediaItem.fromUri("https://video.blender.org/download/videos/3d95fb3d-c866-42c8-9db1-fe82f48ccb95-720.mp4")
//        videoPlayer.setMediaItem(mediaItem)
//        videoPlayer.prepare()
//        videoPlayer.play()
    }

    fun handleAction(action: TopTopAction) {
        when (action) {
            is TopTopAction.LoadData -> {
                val videoId = action.idVideo
            }

            is TopTopAction.ToggleVideo -> {

            }
        }
    }

    private fun loadVideo() {
        _uiState.value = TopTopUiState.Loading
        viewModelScope.launch {
            val listVideo = videoRepository.getListVideo()
            print(listVideo)
            _uiState.value = TopTopUiState.Success
        }
    }

    private fun playVideo() {

    }
}

sealed interface TopTopUiState {
    object Default : TopTopUiState
    object Loading : TopTopUiState
    object Success : TopTopUiState
    data class Error(val msg: String) : TopTopUiState
}

sealed class TopTopAction {
    data class LoadData(val idVideo: Int) : TopTopAction()
    object ToggleVideo : TopTopAction()
}