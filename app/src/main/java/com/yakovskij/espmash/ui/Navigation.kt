package com.yakovskij.espmash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakovskij.espmash.ui.main.MainScreen

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object EspCam : Screen("esp_cam/{deviceUID}")
    data object EspToggle : Screen("esp_toggle/{deviceUID}")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()  // Use rememberNavController() to create the NavHostController
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { MainScreen(navController) }
//        composable(Screen.EspCam.route) { backStackEntry ->
//            val deviceUID = backStackEntry.arguments?.getString("deviceUID") ?: ""
//            EspCamScreen(deviceUID, navController)
//        }
//        composable(Screen.EspToggle.route) { backStackEntry ->
//            val deviceUID = backStackEntry.arguments?.getString("deviceUID") ?: ""
//            EspToggleScreen(deviceUID, navController)
//        }
    }
}
