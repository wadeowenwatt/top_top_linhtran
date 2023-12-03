package wade.owen.toptop.screen.toptop

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
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

@HiltViewModel
class TopTopViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    private val topTopUseCase: TopTopUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(TopTopUiState())
    val uiState: StateFlow<TopTopUiState> get() = _uiState

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
//                    updateLinkVideo(0)
//                    val pageCounting = _uiState.value.pageCounting
//                    val urlVideo =
//                        _uiState.value.listVideoData?.videos?.get(pageCounting)?.video_files?.first()?.link
//                    _uiState.update { currentState ->
//                        currentState.copy(
//                            currentVideoUrl = urlVideo ?: ""
//                        )
//                    }
                }

                is Resource.Error -> {
                    _uiState.value = TopTopUiState(error = resource.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }



    private fun playVideo() {
        val pageCounting = _uiState.value.pageCounting
        val urlVideo =
            _uiState.value.listVideoData?.videos?.get(pageCounting)?.video_files?.first()?.link
        val mediaItem = MediaItem.fromUri(urlVideo ?: "")
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.prepare()
        videoPlayer.play()
    }

    fun updateLinkVideo(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
        val urlVideo =
            _uiState.value.listVideoData?.videos?.get(index)?.video_files?.first()?.link
        _uiState.update { currentState ->
            currentState.copy(
                pageCounting = index,
                currentVideoUrl = urlVideo ?: ""
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false
            )
        }
    }

    fun loading() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
    }
}

data class TopTopUiState(
    var isLoading: Boolean = false,
    var listVideoData: ApiListVideoResponse? = null,
    var pageCounting: Int = 0,
    var currentVideoUrl: String = "",
    var error: String = ""
)
