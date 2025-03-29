package com.yakovskij.espmash.data.model

// Модель устройства
sealed class EspDevice(val ip: String, var name: String = "ESP-Device") {
    val pins: Map<Int, Boolean> = HashMap()
    var isOn = true
    class EspCam(ip: String, name: String = "Esp-Cam") : EspDevice(ip, name)
    class EspToggle(ip: String, name: String = "Esp-Toggle") : EspDevice(ip, name)
}