package com.yakovskij.espmash.ui.espcam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.repository.EspRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EspCamViewModel(private val repository: EspRepository) : ViewModel() {
    private val _device = MutableStateFlow<EspDevice.EspCam?>(null)
    val device: StateFlow<EspDevice.EspCam?> = _device

    fun loadDeviceByName(name: String) {
        // Здесь логика получения устройства (по имени, IP и т.д.)
        viewModelScope.launch {
            val foundDevice = repository.discoverDevices().find { it.name == name } as? EspDevice.EspCam
            _device.value = foundDevice
        }
    }
}
