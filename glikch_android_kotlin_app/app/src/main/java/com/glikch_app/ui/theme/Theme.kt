package com.glikch_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Custom cyberpunk color palette
private val DarkColorScheme = darkColorScheme(
    primary = BloodRed,
    onPrimary = TextWhite,
    background = GothBlack,
    onBackground = TextWhite,
    surface = CardGray,
    onSurface = TextWhite,
    secondary = OrangeAccent
)

@Composable
fun GLIKCH_AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
