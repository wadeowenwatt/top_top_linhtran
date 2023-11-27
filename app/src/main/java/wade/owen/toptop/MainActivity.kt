package wade.owen.toptop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import wade.owen.toptop.compose.BottomBar
import wade.owen.toptop.navigation.NavigationGraph
import wade.owen.toptop.screen.toptop.TopTopScreen
import wade.owen.toptop.ui.theme.TopTopTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopTopTheme {
                // A surface container using the 'background' color from the theme
//                val navController: NavHostController = rememberNavController()
//                val buttonsVisible = remember { mutableStateOf(true) }
//                Scaffold(
//                    bottomBar = {
//                        BottomBar(
//                            navHostController = navController,
//                            state = buttonsVisible,
//                            modifier = Modifier.background(color = Color.White)
//                        )
//                    }
//                ) { paddingValues ->
//                    Box(
//                        modifier = Modifier.padding(paddingValues)
//                    ) {
//                        NavigationGraph(navController = navController)
//                    }
//                }
                TopTopScreen()
            }
        }
    }
}