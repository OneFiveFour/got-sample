package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

/**
 * Displays a description of the coat of arms.
 */
@Composable
fun CoatOfArms(
    modifier: Modifier = Modifier,
    coatOfArms: String
) {

    HeadlineCard(
        modifier = modifier,
        headline = stringResource(id = R.string.coat_of_arms),
        icon = painterResource(id = R.drawable.coat_of_arms)
    ) {
        Text(
            text = coatOfArms,
            style = GoTTheme.typography.small.regular,
            color = GoTTheme.colors.onSecondary
        )

    }

}


@Preview
@Composable
fun CoatOfArmsPreview() {
    CoatOfArms(
        coatOfArms = "A golden wreath, on a blue field with a gold border(Azure, a garland of laurel within a bordure or)"
    )
}