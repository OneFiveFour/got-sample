package io.redandroid.gameofthrones.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val colorPrimary = Color(0xff331C0E)
private val colorPrimaryVariant = Color(0xff67331E)
private val colorOnPrimary = Color(0xffF6F5F5)

private val colorSecondary = Color(0xffFFF190)
private val colorSecondaryVariant = Color(0xffC38D5F)
private val colorOnSecondary = Color(0xff331C0E)

/**
 * All colors that are part of the GoT mobile CD.
 */
class Colors(
    colorPrimary : Color,
    colorPrimaryVariant : Color,
    colorOnPrimary : Color,
    colorSecondary : Color,
    colorSecondaryVariant : Color,
    colorOnSecondary : Color
) {
    var primary by mutableStateOf(colorPrimary)
        private set
    var primaryVariant by mutableStateOf(colorPrimaryVariant)
        private set
    var onPrimary by mutableStateOf(colorOnPrimary)
        private set

    var secondary by mutableStateOf(colorSecondary)
        private set
    var secondaryVariant by mutableStateOf(colorSecondaryVariant)
        private set
    var onSecondary by mutableStateOf(colorOnSecondary)
        private set
}

fun generateColors() = Colors(
    colorPrimary,
    colorPrimaryVariant,
    colorOnPrimary,
    colorSecondary,
    colorSecondaryVariant,
    colorOnSecondary
)

val LocalColors = staticCompositionLocalOf { generateColors() }