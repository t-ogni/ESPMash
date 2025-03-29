package com.yakovskij.espmash.data.repository

import com.yakovskij.espmash.data.model.EspDevice

// Репозиторий (заглушка)
class EspRepository {
    suspend fun discoverDevices(): List<EspDevice> {
        // Здесь должен быть код сканирования сети 192.168.4.0/24
        return listOf(
            EspDevice.EspCam("192.168.4.10"),
            EspDevice.EspToggle("192.168.4.11")
        )
    }
}