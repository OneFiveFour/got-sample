package io.redandroid.gameofthrones.screens.house

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun CoatOfArms(
    modifier: Modifier = Modifier,
    coatOfArms: String
) {

    Row(modifier = modifier
        .background(color = GoTTheme.colors.secondary, shape = RoundedCornerShape(10.dp))
        .padding(8.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.coat_of_arms),
            contentDescription = stringResource(id = R.string.coat_of_arms)
        )

        Spacer(modifier = Modifier.width(8.dp))

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