package wade.owen.toptop.screen.toptop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun TopTopScreen() {
    val topTopViewModel = viewModel(modelClass = TopTopViewModel::class.java)
    val state by topTopViewModel.uiState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)) {
        Text(text = "Toptop")
    }
}