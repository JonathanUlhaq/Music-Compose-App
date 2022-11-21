package com.belajar.spotifykw.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Green200,
    secondary = DarkGrey,
    onSecondary = DarkerGrey,
    background = Black,
    onSurface = SemiWhite,
    surface = LighGrey,
    onBackground = Grey

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SpotifyKwTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
   val ui = rememberSystemUiController()
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        ui.setSystemBarsColor(
            color = Color.Black
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}