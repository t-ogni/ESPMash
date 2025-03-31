package com.yakovskij.espmash.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel


@Composable
fun FeatureConfigView(featureId: Long, viewModel: MainViewModel = getViewModel()) {
    LaunchedEffect(featureId) {
        viewModel.loadFeatureWithConfig(featureId)
    }

    val featureWithConfig by viewModel.featureWithConfig.collectAsState()

    featureWithConfig?.let { (feature, configs) ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Настройки ${feature.type}", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            configs.forEach { config ->
                when (config.key) {
                    "pin" -> Text("Пин: ${config.value}")
                    "state" -> Switch(
                        checked = config.value.toBoolean(),
                        onCheckedChange = { /* Обновить в БД */ }
                    )
                    "resolution" -> Text("Разрешение: ${config.value}")
                    "fps" -> Text("FPS: ${config.value}")
                }
            }
        }
    }
}
