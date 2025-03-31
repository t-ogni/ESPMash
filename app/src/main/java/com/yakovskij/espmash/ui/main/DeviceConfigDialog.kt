package com.yakovskij.espmash.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun DeviceConfigDialog(
    featureId: Long,
    onDismiss: () -> Unit,
    viewModel: MainViewModel = getViewModel()
) {
    // Загружаем данные по устройству
    LaunchedEffect(featureId) {
        viewModel.loadFeatureWithConfig(featureId)
    }

    val featureWithConfig by viewModel.featureWithConfig.collectAsState()

    // Ожидаем загрузки данных
    if (featureWithConfig != null) {
        val (feature, configs) = featureWithConfig!!

        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Настройка ${feature.type}")
            },
            text = {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Тип устройства и его состояние
                    Text(
                        text = "Тип фичи: ${feature.type}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Список конфигураций устройства
                    configs.forEach { config ->
                        when (config.key) {
                            "pin" -> {
                                Text("Пин: ${config.value}")
                            }
                            "state" -> {
                                // Если это устройство с состоянием (например, реле или светодиод)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Состояние: ")
                                    Switch(
                                        checked = config.value.toBoolean(),
                                        onCheckedChange = { checked ->
                                            // Обновление состояния в БД, здесь нужно написать код для сохранения
                                        }
                                    )
                                }
                            }
                            "resolution" -> Text("Разрешение: ${config.value}")
                            "fps" -> Text("FPS: ${config.value}")
                            else -> {
                                // Выводим остальные конфигурации (например, дополнительные пины или функции)
                                Text("${config.key}: ${config.value}")
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Закрыть")
                }
            }
        )
    } else {
        // Пока данные не загружены
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}
