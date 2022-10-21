package io.redandroid.data.converter

import io.redandroid.data.model.Region

/**
 * Converts the API String of a region in Westeros into a [Region]
 */
object RegionConverter {

    /**
     * Converts the API String of a region in Westeros into a [Region]
     */
    fun convert(input: String): Region {
        return when (input) {
            "The Westerlands" -> Region.WESTERLANDS
            "Dorne" -> Region.DORNE
            "The North" -> Region.NORTH
            "The Reach" -> Region.REACH
            "The Vale" -> Region.VALE
            "The Riverlands" -> Region.RIVERLANDS
            "The Crownlands" -> Region.CROWNLANDS
            "The Stormlands" -> Region.STORMLANDS
            "The Neck" -> Region.NECK
            "Iron Islands" -> Region.IRON_ISLANDS
            else -> Region.UNKNOWN
        }
    }

}
