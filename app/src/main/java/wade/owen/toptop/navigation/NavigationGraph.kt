package wade.owen.toptop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import wade.owen.toptop.navigation.Destinations
import wade.owen.toptop.screen.FacebookVideo
import wade.owen.toptop.screen.TopTopScreen

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