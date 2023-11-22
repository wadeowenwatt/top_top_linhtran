package wade.owen.toptop.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun FacebookVideo() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green)) {
        Text(text = "Facebook Video")
    }
}