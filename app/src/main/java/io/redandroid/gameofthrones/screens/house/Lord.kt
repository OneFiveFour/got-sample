package io.redandroid.gameofthrones.screens.house

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

    Row(modifier = modifier
        .fillMaxWidth()
        .background(color = GoTTheme.colors.secondary, shape = RoundedCornerShape(10.dp))
        .padding(8.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.lord),
            contentDescription = stringResource(id = R.string.current_lord)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = lord.name,
                style = GoTTheme.typography.medium.regular,
                color = GoTTheme.colors.onSecondary
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = lord.titles.joinToString(",\n"),
                style = GoTTheme.typography.small.regularItalic,
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