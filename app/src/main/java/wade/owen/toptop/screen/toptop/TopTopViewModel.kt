package wade.owen.toptop.screen.toptop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
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


@HiltViewModel
@OptIn(ExperimentalFoundationApi::class)
class TopTopViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    private val topTopUseCase: TopTopUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(TopTopUiState())
    val uiState: StateFlow<TopTopUiState> get() = _uiState
    var currentPage = 0
    val pagerState: PagerState
        @Composable
        get() = rememberPagerState(
            initialPage = 0,
            pageCount = {
                15
            })

    init {
        prepareVideoPlayer()
        getListVideo()
    }

    private fun getListVideo() {
        topTopUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _uiState.value = TopTopUiState(isLoading = true)
                }

                is Resource.Success -> {
                    _uiState.value = TopTopUiState(listVideoData = resource.data)
                    getVideoWithPagerNumber(0)
                }

                is Resource.Error -> {
                    _uiState.value = TopTopUiState(error = resource.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setLoadingState() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
    }

    fun updateCurrentVideoUrl(urlVideo: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentVideoUrl = urlVideo,
            )
        }
        playVideo()
    }

    fun getVideoWithPagerNumber(pager: Int) {
        val videoFile = _uiState.value.listVideoData?.videos?.get(pager)?.video_files
        val videoUrl = videoFile?.first()?.link
        updateCurrentVideoUrl(videoUrl ?: "")
    }

    fun swipeChangeVideo(settledPage: Int) {
        setLoadingState()
        currentPage = settledPage
        getVideoWithPagerNumber(settledPage)
    }

    private fun prepareVideoPlayer() {
        videoPlayer.addAnalyticsListener(EventLogger())
        videoPlayer.repeatMode = REPEAT_MODE_ALL
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()
    }

    private fun playVideo() {
        val mediaItem = MediaItem.fromUri(_uiState.value.currentVideoUrl)
        videoPlayer.setMediaItem(mediaItem, true)
        videoPlayer.play()
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false,
            )
        }
    }
    private fun playVideo(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.play()
    }
}


data class TopTopUiState(
    var isLoading: Boolean = false,
    var listVideoData: ApiListVideoResponse? = null,
    var pageCounting: Int = 0,
    var currentVideoUrl: String = "",
    var error: String = ""
)
