package com.yakovskij.espmash.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yakovskij.espmash.data.model.EspDevice

@Composable
fun DeviceItem(device: EspDevice, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(device.name, style = MaterialTheme.typography.headlineMedium)
            Text(device.ip, style = MaterialTheme.typography.bodyMedium)
        }
    }
}