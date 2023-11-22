package wade.owen.toptop.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null,
) {
    object TopTopScreen : Destinations(
        route = "top_top_screen",
        title = "Top Top",
        icon = Icons.Outlined.Home,
    )

    object FacebookVideo : Destinations(
        route = "facebook_video",
        title = "Facebook Video",
        icon = Icons.Outlined.ThumbUp,
    )
}