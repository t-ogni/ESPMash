package com.yakovskij.espmash.ui.main

import EspRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.model.EspFeature
import com.yakovskij.espmash.data.model.EspFeatureConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: EspRepository) : ViewModel() {
    private val _devicesWithFeatures = MutableStateFlow<List<Pair<EspDevice, List<EspFeature>>>>(emptyList())
    val devicesWithFeatures: StateFlow<List<Pair<EspDevice, List<EspFeature>>>> = _devicesWithFeatures.asStateFlow()

    private val _featureWithConfig = MutableStateFlow<Pair<EspFeature, List<EspFeatureConfig>>?>(null)
    val featureWithConfig: StateFlow<Pair<EspFeature, List<EspFeatureConfig>>?> = _featureWithConfig




        // Загрузка всех устройств с фичами
        fun loadDevicesWithFeatures() {
            viewModelScope.launch {
                val devices = repository.getAllDevices() // Получаем список устройств
                val devicesWithFeatures = devices.map { device ->
                    val features = repository.getFeaturesForDevice(device.id) // Получаем фичи для каждого устройства
                    device to features
                }
                _devicesWithFeatures.value = devicesWithFeatures
            }
        }

        // Загрузка фичи с конфигурацией
        fun loadFeatureWithConfig(featureId: Long) {
            viewModelScope.launch {
                repository.getFeatureWithConfigFlow(featureId).collect { data ->
                    _featureWithConfig.value = data
                }
            }
        }

        // Обновление списка устройств
        fun refreshListDevices() {
            viewModelScope.launch {
                val devices = repository.getAllDevices()
                _devicesWithFeatures.value = devices.map { device ->
                    val features = repository.getFeaturesForDevice(device.id)
                    device to features
                }
            }
        }



}
