package com.yakovskij.espmash.ui.main

import DeviceItem
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = getViewModel()) {
    // Получаем список устройств с их фичами
    val devicesWithFeatures by viewModel.devicesWithFeatures.collectAsState()

    var selectedDeviceId by remember { mutableStateOf<Long?>(null) }
    var showDeviceConfigDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ESP-Mash") },
                actions = {
                    IconButton(onClick = { viewModel.refreshListDevices() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(devicesWithFeatures) { deviceWithFeatures ->
                // Отправляем в DeviceItem пару (устройство + фичи)
                DeviceItem(deviceWithFeatures = deviceWithFeatures, onClick = {
                    selectedDeviceId = deviceWithFeatures.first.id
                    showDeviceConfigDialog = true
                })
            }
        }
    }

    // Если выбранное устройство не null, открываем модальное окно
    if (showDeviceConfigDialog && selectedDeviceId != null) {
        DeviceConfigDialog(
            featureId = selectedDeviceId!!,
            onDismiss = { showDeviceConfigDialog = false }
        )
    }
}
