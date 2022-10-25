package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.Person
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun Lord(
    modifier: Modifier = Modifier,
    lord: Person
) {

    HeadlineCard(
        modifier = modifier,
        headline = stringResource(id = R.string.current_lord),
        icon = painterResource(id = R.drawable.current_lord)
    ) {

        Column {
            Text(
                text = lord.name,
                style = GoTTheme.typography.medium.regular,
                color = GoTTheme.colors.onSecondary
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = lord.titles.joinToString(",\n"),
                style = GoTTheme.typography.small.regular,
                color = GoTTheme.colors.onSecondary
            )
        }

    }

}


@Preview
@Composable
fun LordPreview() {
    Lord(
        lord = Person(
            name = "Delonne Allyrion",
            titles = listOf(
                "Lady of Godsgrace"
            )
        )
    )
}