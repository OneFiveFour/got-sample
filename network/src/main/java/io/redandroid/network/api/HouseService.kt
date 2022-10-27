package io.redandroid.network.api

import javax.inject.Inject

/**
 * This interface describes all public calls regarding houses in Game of Thrones.
 */
class HouseService @Inject constructor(
    private val api: GameOfThronesApi
) {

    /**
     * @return a paged list of houses in Game of Thrones.
     * @param page the page number to be fetched. If null, the first page will load.
     */
    suspend fun getHouses(page: Int?) = api.getHouses(page)

    /**
     * @return the house with the given [id].
     */
    suspend fun getHouse(id: Int) = api.getHouse(id)

}