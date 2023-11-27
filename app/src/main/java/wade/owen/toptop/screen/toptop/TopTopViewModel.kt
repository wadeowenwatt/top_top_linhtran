package wade.owen.toptop.screen.toptop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import wade.owen.toptop.data.model.ApiListVideoResponse
import wade.owen.toptop.data.repositories.VideoRepository
import javax.inject.Inject

@HiltViewModel
class TopTopViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    private val videoRepository: VideoRepository,
): ViewModel() {
    private var _uiState = MutableStateFlow<TopTopUiState>(TopTopUiState.Default) /// mutable Livedata
    val uiState: StateFlow<TopTopUiState> get() = _uiState /// Live Data

    init {
        viewModelScope.launch {
            val listVideo = videoRepository.getListVideo()
            print(listVideo)
        }
    }
}

sealed interface TopTopUiState {
    object Default: TopTopUiState
    object Loading: TopTopUiState
    object Success: TopTopUiState
    data class Error(val msg: String): TopTopUiState
}