package io.redandroid.gameofthrones.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * The sub-variants of typography in the app.
 */
class GoTTextStyle(val textSize: TextUnit) {

    val regular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSize
    )
    val medium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = textSize
    )
    val bold = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = textSize
    )
    val mediumItalic = medium.copy(
        fontStyle = FontStyle.Italic
    )

}

/**
 * The main variants of the typography in the app.
 */
data class GoTTypography(
    val large: GoTTextStyle = GoTTextStyle(32.sp),
    val medium: GoTTextStyle = GoTTextStyle(18.sp),
    val small: GoTTextStyle = GoTTextStyle(12.sp)
)

internal val LocalTypography = staticCompositionLocalOf { GoTTypography() }