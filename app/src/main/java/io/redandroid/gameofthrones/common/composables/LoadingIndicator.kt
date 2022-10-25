package io.redandroid.gameofthrones.common.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

/**
 * An indeterminate loading indicator to show, while the app is fetching data.
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = R.string.loading),
            style = GoTTheme.typography.large.regular,
            color = GoTTheme.colors.onSecondary,
            textAlign = TextAlign.Center
        )


        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50)),
            color = GoTTheme.colors.primary
        )

    }

}

@Preview
@Composable
fun LoadingIndicatorPreview() {
    LoadingIndicator()
}