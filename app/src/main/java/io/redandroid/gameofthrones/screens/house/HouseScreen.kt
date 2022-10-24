package io.redandroid.gameofthrones.screens.house

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun HouseScreen(
    houseUiState: HouseUiState
) {

    Box {

        if (houseUiState.errorMessage.isNotEmpty()) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "ERROR",
                style = GoTTheme.typography.large.regular,
                color = GoTTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )

        } else if (houseUiState.isLoading) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "LOADING",
                style = GoTTheme.typography.large.regular,
                color = GoTTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )

        } else {

            House(houseUiState)

        }

    }

}

@Preview
@Composable
fun HouseScreenPreview() {
    HouseScreen(
        houseUiState = HouseUiState(
            house = getHousePreview()
        )
    )
}

@Preview
@Composable
fun HouseScreenPreviewLoading() {
    HouseScreen(
        houseUiState = HouseUiState(
            isLoading = true
        )
    )
}

@Preview
@Composable
fun HouseScreenPreviewError() {
    HouseScreen(
        houseUiState = HouseUiState(
            errorMessage = "The Server did not respond. Please try again later."
        )
    )
}