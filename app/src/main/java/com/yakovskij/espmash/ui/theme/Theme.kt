package com.yakovskij.espmash.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Определение цветовых схем
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),  // Фиолетовый
    secondary = Color(0xFF03DAC5), // Бирюзовый
    background = Color(0xFF121212), // Тёмный фон
    surface = Color(0xFF1E1E1E), // Тёмная поверхность
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),  // Фиолетовый
    secondary = Color(0xFF03DAC5), // Бирюзовый
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Основная тема
@Composable
fun EspMashTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Автоматическое переключение по системным настройкам
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography, // Используем кастомную типографику
        content = content
    )
}
