package io.redandroid.gameofthrones.screens.house

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.House
import io.redandroid.data.model.Region
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.map.Westeros
import io.redandroid.gameofthrones.theme.GoTTheme

@Composable
fun HouseDetails(houseUiState: HouseUiState) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = houseUiState.house.name,
            style = GoTTheme.typography.large.regular,
            color = GoTTheme.colors.onSecondary,
            textAlign = TextAlign.Center
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {


            Column(modifier = Modifier.weight(1f)) {

                Westeros(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    highlightedRegions = listOf(houseUiState.house.region)
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = toRegionName(houseUiState.house.region)),
                    style = GoTTheme.typography.small.regular,
                    color = GoTTheme.colors.onSecondary,
                    textAlign = TextAlign.Center
                )

            }

            Column(modifier = Modifier.weight(1f)) {

                if (houseUiState.house.words.isNotEmpty()) {
                    Quote(
                        modifier = Modifier.fillMaxWidth(),
                        text = houseUiState.house.words
                    )
                }

            }

        }

    }

}

fun toRegionName(region: Region): Int {
    return when (region) {
        Region.CROWNLANDS -> R.string.crownlands
        Region.DORNE -> R.string.dorne
        Region.IRON_ISLANDS -> R.string.iron_islands
        Region.NORTH -> R.string.north
        Region.REACH -> R.string.reach
        Region.RIVERLANDS -> R.string.riverlands
        Region.STORMLANDS -> R.string.stormlands
        Region.VALE -> R.string.vale
        Region.WESTERLANDS -> R.string.westerlands
        Region.NECK -> R.string.neck
        Region.UNKNOWN -> R.string.unknown
    }
}


@Preview
@Composable
fun HouseDetailPreview() {
    val houseUiState = HouseUiState(
        house = House(
            1, "Test House", Region.STORMLANDS, "No Foe May Pass"
        )
    )

    Box(modifier = Modifier.background(Color.White)) {
        HouseDetails(houseUiState)
    }
}