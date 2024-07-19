package com.makashovadev.wifianalyzer.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary =  primary_dark,
    secondary = secondary_dark,
    tertiary = tertiary_dark,
    onPrimary = on_primary_dark,
    onSecondary = on_secondary_dark,
    onTertiary = on_tertiary_dark,
    background = background_dark,
    surface = surface_dark,
    inversePrimary = inverse_primary_dark,
    error = error_dark
)

private val LightColorScheme = lightColorScheme(
    primary = primary_light,
    secondary = secondary_light,
    tertiary = tertiary_light,
    onPrimary = on_primary_light,
    onSecondary = on_secondary_light,
    onTertiary = on_tertiary_light,
    background = background_light,
    surface = surface_light,
    inversePrimary = inverse_primary_light,
    error = error_light
)

@SuppressLint("SuspiciousIndentation")
@Composable
fun WifiAnalyzerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        //typography = kotlin.text.Typography,
        content = content
    )
}