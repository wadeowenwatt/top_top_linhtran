package wade.owen.toptop.screen.toptop

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wade.owen.toptop.data.repositories.VideoRepository
import javax.inject.Inject

@HiltViewModel
class TopTopViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    val videoRepository: VideoRepository,
): ViewModel() {
    private var _uiState = MutableStateFlow<TopTopUiState>(TopTopUiState.Default) /// mutable Livedata
     val uiState: StateFlow<TopTopUiState> get() = _uiState /// Live Data

    init {

    }
}

sealed interface TopTopUiState {
    object Default: TopTopUiState
    object Loading: TopTopUiState
    object Success: TopTopUiState
    data class Error(val msg: String): TopTopUiState
}