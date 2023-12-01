package wade.owen.toptop.screen.toptop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopTopViewModel @Inject constructor() : ViewModel() {
    @OptIn(ExperimentalFoundationApi::class)
    val pagerController: PagerState
        @Composable
        get() = rememberPagerState(pageCount = {
            10
        })
}