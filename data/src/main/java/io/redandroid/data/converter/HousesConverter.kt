package io.redandroid.data.converter

import io.redandroid.data.model.House
import io.redandroid.network.model.Houses as NetworkHouses

/**
 * Used to convert [NetworkHouses] into a list of [House]
 */
object HousesConverter {

    /**
     * @return a list of [House]s converted from the given [input]
     */
    fun convert(input: NetworkHouses) : List<House> {
        return input.map { house -> HouseConverter.convert(house) }
    }


}
