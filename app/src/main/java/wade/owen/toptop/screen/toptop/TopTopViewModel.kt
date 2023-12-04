package wade.owen.toptop.screen.toptop

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
//    val videoPlayer: ExoPlayer,
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
    }

    fun getVideoWithPagerNumber(pager: Int) {
        val videoFile = _uiState.value.listVideoData?.videos?.get(pager)?.video_files
        val videoUrl = videoFile?.first()?.link
        updateCurrentVideoUrl(videoUrl ?: "")
    }

    fun swipeChangeVideo(settledPage: Int) {
        currentPage = settledPage
        getVideoWithPagerNumber(settledPage)
    }
}


data class TopTopUiState(
    var isLoading: Boolean = false,
    var listVideoData: ApiListVideoResponse? = null,
    var pageCounting: Int = 0,
    var currentVideoUrl: String = "",
    var error: String = ""
)
