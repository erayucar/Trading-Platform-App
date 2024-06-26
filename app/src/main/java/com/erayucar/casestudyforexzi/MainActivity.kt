package com.erayucar.casestudyforexzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erayucar.casestudyforexzi.ui.SplashScreen
import com.erayucar.casestudyforexzi.ui.orderBook.OrderBookScreen
import com.erayucar.casestudyforexzi.ui.pair.PairListScreen
import com.erayucar.casestudyforexzi.ui.panel.TradePanelScreen
import com.erayucar.casestudyforexzi.ui.theme.CaseStudyForExziTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaseStudyForExziTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Mainscreen()
                }
            }
        }
    }
}

@Composable
fun Mainscreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            SplashScreen {
                navController.navigate("pairList")
            }
        }
        composable("pairList"){
            PairListScreen {
                navController.navigate("orderBook/${it._id}")
            }
        }
        composable("orderBook/{pairID}", arguments = listOf(
            navArgument("pairID"){
                type = NavType.StringType
            }
        )){
            val pairID = it.arguments?.getString("pairID") ?: "1041"
            OrderBookScreen(pairID = pairID, onBuyClick = {
                navController.navigate("panel/$pairID")
            }){
                navController.popBackStack()
            }
        }
        composable("panel/{pairID}", arguments = listOf(
            navArgument("pairID"){
                type = NavType.StringType
            }
        )){
            val pairID = it.arguments?.getString("pairID") ?: "1041"
            TradePanelScreen(pairID = pairID)
        }

    }
}

