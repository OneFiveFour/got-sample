package io.redandroid.gameofthrones.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoTTheme(
    colors: Colors = GoTTheme.colors,
    typography: GoTTypography = GoTTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalContentColor provides GoTTheme.colors.onPrimary,
        LocalOverscrollConfiguration provides null
    ) {
        content()
    }
}