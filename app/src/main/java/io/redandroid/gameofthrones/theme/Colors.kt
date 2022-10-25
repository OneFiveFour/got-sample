package io.redandroid.gameofthrones.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val colorPrimaryLight = Color(0xff331C0E)
private val colorPrimaryVariantLight = Color(0xff67331E)
private val colorOnPrimaryLight = Color(0xffF6F5F5)
private val colorSecondaryLight = Color(0xFFD7DCE4)

private val colorPrimaryDark = Color(0xff716646)
private val colorPrimaryVariantDark = Color(0xff8C8C8C)
private val colorOnPrimaryDark = Color(0xffECD6AE)
private val colorSecondaryDark = Color(0xFF1E2F51)

/**
 * All colors that are part of the GoT mobile CD.
 */
class Colors(
    colorPrimary : Color,
    colorPrimaryVariant : Color,
    colorOnPrimary : Color,
    colorSecondary : Color
) {
    var primary by mutableStateOf(colorPrimary)
        private set
    var primaryVariant by mutableStateOf(colorPrimaryVariant)
        private set
    var onPrimary by mutableStateOf(colorOnPrimary)
        private set

    var secondary by mutableStateOf(colorSecondary)
        private set
    var secondaryVariant by mutableStateOf(colorOnPrimary)
        private set
    var onSecondary by mutableStateOf(colorPrimary)
        private set
}

fun generateColors(isDarkTheme: Boolean) = if (isDarkTheme) {
    Colors(
        colorPrimaryDark,
        colorPrimaryVariantDark,
        colorOnPrimaryDark,
        colorSecondaryDark
    )
} else {
    Colors(
        colorPrimaryLight,
        colorPrimaryVariantLight,
        colorOnPrimaryLight,
        colorSecondaryLight
    )
}

internal val LocalColors = staticCompositionLocalOf { generateColors(false) }