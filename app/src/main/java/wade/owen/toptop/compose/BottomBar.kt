package wade.owen.toptop.compose

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import wade.owen.toptop.navigation.Destinations

@Composable
fun BottomBar(
    navHostController: NavHostController,
    state: MutableState<Boolean>,
    modifier: Modifier
) {

    val screens = listOf(
        Destinations.TopTopScreen, Destinations.FacebookVideo,
    )

    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = screen.icon!!, contentDescription = "") },
                label = {
                    Text(text = screen.title!!)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color.Gray,
                    unselectedTextColor = Color.White,
                )
            )
        }
    }

}