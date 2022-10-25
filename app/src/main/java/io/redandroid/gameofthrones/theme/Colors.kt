package io.redandroid.gameofthrones.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val colorPrimary = Color(0xff331C0E)
private val colorOnPrimary = Color(0xffF6F5F5)

private val colorSecondary = Color(0xFFD7DCE4)
private val colorOnSecondary = Color(0xff331C0E)

/**
 * All colors that are part of the GoT mobile CD.
 */
class Colors(
    colorPrimary : Color,
    colorOnPrimary : Color,
    colorSecondary : Color,
    colorOnSecondary : Color
) {
    var primary by mutableStateOf(colorPrimary)
        private set
    var onPrimary by mutableStateOf(colorOnPrimary)
        private set

    var secondary by mutableStateOf(colorSecondary)
        private set
    var onSecondary by mutableStateOf(colorOnSecondary)
        private set
}

fun generateColors() = Colors(
    colorPrimary,
    colorOnPrimary,
    colorSecondary,
    colorOnSecondary
)

val LocalColors = staticCompositionLocalOf { generateColors() }