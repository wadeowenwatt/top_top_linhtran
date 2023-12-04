package wade.owen.toptop.screen.toptop.video

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import wade.owen.toptop.config.Resource
import wade.owen.toptop.data.model.ApiListVideoResponse
import wade.owen.toptop.domain.use_case.TopTopUseCase
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class VideoViewModel @OptIn(UnstableApi::class) @Inject constructor(
    val videoPlayer: ExoPlayer,
//    private val topTopUseCase: TopTopUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<VideoUiState>(VideoUiState.Default) /// mutable Livedata
    val uiState: StateFlow<VideoUiState> get() = _uiState /// Live Data

    init {
        prepareVideoPlayer()
    }

    private fun playVideo(urlVideo: String?) {
        val mediaItem = MediaItem.fromUri(urlVideo ?: "")
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.play()
    }

    private fun prepareVideoPlayer() {
        setLoadingState()
        if (videoPlayer.isLoading) {
            setLoadingState()
        }
        videoPlayer.repeatMode = REPEAT_MODE_ALL
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()
    }

    private fun toggleVideo() {
        if (videoPlayer.isPlaying)
            videoPlayer.pause() else videoPlayer.play()
    }

    fun handleAction(action: VideoAction) {
        when (action) {
            is VideoAction.ToggleVideo -> {
                toggleVideo()
            }
        }
    }

    fun updateState(urlVideo: String) {
        _uiState.update {
            VideoUiState.Success(
                urlVideo = urlVideo
            )
        }
        playVideo((_uiState.value as VideoUiState.Success).urlVideo)
    }

    private fun setLoadingState() {
        _uiState.update {
            VideoUiState.Loading
        }
    }

    override fun onCleared() {
        super.onCleared()
        videoPlayer.release()
    }
}

sealed class VideoUiState {
    object Default : VideoUiState()
    object Loading : VideoUiState()
    data class Success(
        var urlVideo: String = "",
    ) : VideoUiState()

    data class Failure(
        var message: String = "",
    ) : VideoUiState()
}

sealed class VideoAction {
    object ToggleVideo : VideoAction()
}