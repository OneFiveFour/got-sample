package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.composables.AutoSizeText
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun HeadlineCard(
    modifier: Modifier = Modifier,
    headline: String,
    icon: Painter,
    content: @Composable BoxScope.() -> Unit = { }
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = GoTTheme.colors.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 8.dp)
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            Image(painter = icon, contentDescription = null)

            Text(
                text = headline,
                style = GoTTheme.typography.large.regular,
                color = GoTTheme.colors.onSecondary
            )

        }

        Box(modifier = Modifier.padding(
            start = 18.dp,
            top = 8.dp,
            bottom = 8.dp,
            end = 18.dp
        )) {
            content()
        }

    }

}

@Preview
@Composable
fun HeadlineCardPreview() {
    HeadlineCard(
        headline ="Headline",
        icon = painterResource(id = R.drawable.coat_of_arms)
    ) {
        Text(text = "Test test")
    }
}