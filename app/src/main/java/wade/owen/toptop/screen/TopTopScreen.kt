package wade.owen.toptop.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun TopTopScreen() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
        Text(text = "Toptop")
    }
}