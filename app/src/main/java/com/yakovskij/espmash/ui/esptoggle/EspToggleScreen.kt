package com.yakovskij.espmash.ui.esptoggle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.viewModel

@Composable
fun EspToggleScreen(deviceName: String, navController: NavController, viewModel: Lazy<EspToggleViewModel> = viewModel()) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("ESP-Toggle: $deviceName")
        repeat(4) { pin ->
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text("Пин $pin")
                Switch(checked = false, onCheckedChange = { /* TODO: Управление пином */ })
            }
        }
    }
}
