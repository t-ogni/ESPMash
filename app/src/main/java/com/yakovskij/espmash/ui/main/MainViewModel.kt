package com.yakovskij.espmash.ui.main
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.repository.EspRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: EspRepository) : ViewModel() {
    private val _devices = MutableStateFlow<List<EspDevice>>(emptyList())
    val devices: StateFlow<List<EspDevice>> = _devices.asStateFlow()

    fun loadDevices() {
        viewModelScope.launch {
            _devices.value = repository.discoverDevices()
        }
    }
}
