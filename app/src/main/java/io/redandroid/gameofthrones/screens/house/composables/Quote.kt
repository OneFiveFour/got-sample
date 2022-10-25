package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun Quote(
    modifier: Modifier = Modifier,
    text: String
) {

    Column(modifier = modifier
        .fillMaxWidth()
        .background(color = GoTTheme.colors.secondary, shape = RoundedCornerShape(10.dp))
        .padding(8.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.quote_begin),
            contentDescription = null
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = GoTTheme.typography.medium.regular,
            color = GoTTheme.colors.onSecondary,
            textAlign = TextAlign.Center
        )

        Image(
            modifier = Modifier.align(Alignment.End),
            painter = painterResource(id = R.drawable.quote_end),
            contentDescription = null
        )
    }


}


@Preview
@Composable
fun QuotePreview() {
    Quote(
        text = "No Foe May Pass"
    )
}