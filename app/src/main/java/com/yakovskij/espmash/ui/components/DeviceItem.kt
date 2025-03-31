
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yakovskij.espmash.data.model.EspDevice
import com.yakovskij.espmash.data.model.EspFeature

@Composable
fun DeviceItem(deviceWithFeatures: Pair<EspDevice, List<EspFeature>>, onClick: () -> Unit) {
    val device = deviceWithFeatures.first
    val features = deviceWithFeatures.second

    // Проверка, если у устройства есть камера или реле
    val hasCamera = features.any { it.type == "camera" }
    val hasRelay = features.any { it.type == "relay" }

    // Определяем иконку в зависимости от фич
    val icon = when {
        hasCamera -> Icons.Default.Face  // Камера
        hasRelay -> Icons.Outlined.Build  // Реле
        else -> Icons.Default.FavoriteBorder  // Дефолтная иконка
    }

    // Определение индикатора состояния устройства (например, в зависимости от доступности)
    val status = if (features.any { it.type == "online" }) "online" else "offline"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = device.name, style = MaterialTheme.typography.titleMedium)
                Text(text = device.ip, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            // Показываем индикатор состояния
            DeviceStatusIndicator(status = status)
        }
    }
}

@Composable
fun DeviceStatusIndicator(status: String) {
    val color = when (status) {
        "online" -> Color.Green
        "offline" -> Color.Red
        else -> Color.Gray
    }

    // Статус будет представлен как кружок
    Spacer(
        modifier = Modifier
            .size(10.dp)
            .padding(start = 8.dp)
            .background(color, shape = MaterialTheme.shapes.small)
    )
}
