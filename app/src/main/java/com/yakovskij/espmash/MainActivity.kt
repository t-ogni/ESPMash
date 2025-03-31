package com.yakovskij.espmash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.yakovskij.espmash.ui.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EspMashTheme {
                NavGraph() // Запускаем навигацию внутри темы
            }
        }
    }
}
