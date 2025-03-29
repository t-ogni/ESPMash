package com.yakovskij.espmash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakovskij.espmash.ui.espcam.EspCamScreen
import com.yakovskij.espmash.ui.esptoggle.EspToggleScreen
import com.yakovskij.espmash.ui.main.MainScreen

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object EspCam : Screen("esp_cam/{device}")
    data object EspToggle : Screen("esp_toggle/{device}")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()  // Use rememberNavController() to create the NavHostController
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.EspCam.route) { backStackEntry ->
            val deviceName = backStackEntry.arguments?.getString("device") ?: ""
            EspCamScreen(deviceName, navController)
        }
        composable(Screen.EspToggle.route) { backStackEntry ->
            val deviceName = backStackEntry.arguments?.getString("device") ?: ""
            EspToggleScreen(deviceName, navController)
        }
    }
}
