package com.yakovskij.espmash.ui.espcam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.viewModel

import androidx.navigation.NavController
import com.yakovskij.espmash.data.model.EspDevice

@Composable
fun EspCamScreen(device: EspDevice.EspCam) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("ESP-Cam: ${device.name}")
        Switch(checked = device.isOn, onCheckedChange = { /* TODO: Handle switch */ })
        Button(onClick = { /* TODO: Open RTSP */ }) {
            Text("Открыть RTSP")
        }
    }
}

@Composable
fun EspCamScreen(deviceName: String, navController: NavController, viewModel: EspCamViewModel = viewModel()) {
    val device by viewModel.device.collectAsState()

    LaunchedEffect(deviceName) {
        viewModel.loadDeviceByName(deviceName)
    }

    if (device != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ESP-Cam: ${device?.name}")
            // Логика для управления камерой
        }
    } else {
        // Показать индикатор загрузки или ошибку, если устройство не найдено
        Text("Loading device...")
    }
}

