package io.redandroid.gameofthrones.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import io.redandroid.gameofthrones.R

/**
 * The sub-variants of typography in the app.
 */
class GoTTextStyle(textSize: TextUnit) {
    val regular = TextStyle(
        fontFamily = FontFamily(Font(
            R.font.junge,
            FontWeight.Normal
        )),
        fontSize = textSize
    )

    @OptIn(ExperimentalUnitApi::class)
    val condensed = TextStyle(
        fontFamily = FontFamily(Font(
            R.font.junge,
            FontWeight.Normal
        )),
        fontSize = textSize,
        letterSpacing = TextUnit(-4f, TextUnitType.Sp)
    )
}

/**
 * The main variants of the typography in the app.
 */
data class GoTTypography(
    val xlarge: GoTTextStyle = GoTTextStyle(48.sp),
    val large: GoTTextStyle = GoTTextStyle(24.sp),
    val medium: GoTTextStyle = GoTTextStyle(20.sp),
    val small: GoTTextStyle = GoTTextStyle(14.sp)
)

internal val LocalTypography = staticCompositionLocalOf { GoTTypography() }