package io.redandroid.gameofthrones.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoTTheme(
    isDarkTheme : Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val themedColors = generateColors(isDarkTheme)

    CompositionLocalProvider(
        LocalColors provides themedColors,
        LocalTypography provides GoTTheme.typography,
        LocalContentColor provides GoTTheme.colors.onPrimary,
        LocalOverscrollConfiguration provides null
    ) {
        content()
    }
}


/**
 * The main theme of the app.
 */
object GoTTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: GoTTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
