package wade.owen.toptop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import wade.owen.toptop.screen.facebook.FacebookVideo
import wade.owen.toptop.screen.toptop.TopTopScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.TopTopScreen.route) {
        composable(Destinations.TopTopScreen.route) {
            TopTopScreen()
        }
        composable(Destinations.FacebookVideo.route) {
            FacebookVideo()
        }
    }
}