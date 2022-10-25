package io.redandroid.gameofthrones.common.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.Region
import io.redandroid.gameofthrones.R

private var regions : List<Region> = emptyList()

private fun getAlpha(region: Region) = if (regions.contains(region)) 1f else 0.3f

@Composable
fun Westeros(
    modifier: Modifier = Modifier,
    highlightedRegions : List<Region> = Region.values().toList()
) {

    regions = highlightedRegions

    Box(modifier = modifier) {

        Image(
            painter = painterResource(id = R.drawable.westeros_north),
            contentDescription = stringResource(id = R.string.north),
            alpha = getAlpha(Region.NORTH)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_iron_islands),
            contentDescription = stringResource(id = R.string.iron_islands),
            alpha = getAlpha(Region.IRON_ISLANDS)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_riverlands),
            contentDescription = stringResource(id = R.string.riverlands),
            alpha = getAlpha(Region.RIVERLANDS)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_vale),
            contentDescription = stringResource(id = R.string.vale),
            alpha = getAlpha(Region.VALE)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_westerlands),
            contentDescription = stringResource(id = R.string.westerlands),
            alpha = getAlpha(Region.WESTERLANDS)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_reach),
            contentDescription = stringResource(id = R.string.reach),
            alpha = getAlpha(Region.REACH)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_crownlands),
            contentDescription = stringResource(id = R.string.crownlands),
            alpha = getAlpha(Region.CROWNLANDS)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_stormlands),
            contentDescription = stringResource(id = R.string.stormlands),
            alpha = getAlpha(Region.STORMLANDS)
        )

        Image(
            painter = painterResource(id = R.drawable.westeros_dorne),
            contentDescription = stringResource(id = R.string.dorne),
            alpha = getAlpha(Region.DORNE)
        )

    }

}




@Preview
@Composable
fun WesterosPreview() {
    Westeros()
}