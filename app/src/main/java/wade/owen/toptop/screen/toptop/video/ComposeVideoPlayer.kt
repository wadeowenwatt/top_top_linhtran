package wade.owen.toptop.screen.toptop.video

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun ComposeVideoPlayer(modifier: Modifier, player: Player) {
    AndroidView(
        factory = { context ->
            PlayerView(context).also {
                it.player = player
                it.useController = true
                it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            }
        },
        modifier = modifier,
    )
}