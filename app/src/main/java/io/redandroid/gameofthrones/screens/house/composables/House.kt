package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.Region
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.composables.AutoSizeText
import io.redandroid.gameofthrones.common.composables.FontSizeRange
import io.redandroid.gameofthrones.common.composables.Westeros
import io.redandroid.gameofthrones.screens.house.HouseUiState
import io.redandroid.gameofthrones.theme.GoTTheme

/**
 * Displays the details of a house in Game of Thrones.
 */
@Composable
fun House(houseUiState: HouseUiState) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {

        // NAME OF HOUSE
        AutoSizeText(
            modifier = Modifier.fillMaxWidth(0.8f).padding(top = 16.dp),
            text = houseUiState.house.name,
            color = GoTTheme.colors.onSecondary,
            style = GoTTheme.typography.xlarge.condensed,
            fontSizeRange = FontSizeRange(
                min = GoTTheme.typography.small.regular.fontSize,
                max = GoTTheme.typography.xlarge.condensed.fontSize
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        // MAP OF WESTEROS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = GoTTheme.colors.secondary,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp),
            verticalAlignment = Alignment.Bottom,
        ) {

            Westeros(
                modifier = Modifier.weight(1f),
                highlightedRegions = listOf(houseUiState.house.region)
            )

            Text(
                modifier = Modifier
                    .weight(1.3f)
                    .padding(start = 8.dp),
                text = stringResource(id = toRegionName(houseUiState.house.region)),
                style = GoTTheme.typography.large.regular,
                color = GoTTheme.colors.onSecondary,
            )

        }

        if (houseUiState.house.words.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Quote(
                modifier = Modifier.fillMaxWidth(),
                text = houseUiState.house.words
            )
        }

        if (houseUiState.house.coatOfArms.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            CoatOfArms(coatOfArms = houseUiState.house.coatOfArms)
        }

        if (houseUiState.house.currentLord.name.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Lord(lord = houseUiState.house.currentLord)
        }

        if (houseUiState.house.swornMembers.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            SwornMembers(swornMembers = houseUiState.house.swornMembers)
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
        house = getHousePreview()
    )

    Box(modifier = Modifier.background(Color.White)) {
        House(houseUiState)
    }
}