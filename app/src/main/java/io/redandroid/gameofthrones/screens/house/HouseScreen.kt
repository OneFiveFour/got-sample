package io.redandroid.gameofthrones.screens.house

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.House
import io.redandroid.data.model.Region
import io.redandroid.gameofthrones.common.map.Westeros
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun HouseScreen(
    houseUiState: HouseUiState
) {

    Column {

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

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = houseUiState.house.name,
                style = GoTTheme.typography.large.regular,
                color = GoTTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )

            Westeros(
                highlightedRegions = listOf(houseUiState.house.region)
            )
        }

    }

}

@Preview
@Composable
fun HouseScreenPreview() {
    HouseScreen(
        houseUiState = HouseUiState(
            house = House(1, "Test House", Region.STORMLANDS)
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