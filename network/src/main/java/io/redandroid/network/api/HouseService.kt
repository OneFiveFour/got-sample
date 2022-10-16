package io.redandroid.network.api

import javax.inject.Inject

/**
 * This interface describes all public calls regarding houses in Game of Thrones.
 */
class HouseService @Inject constructor(
    val api: GameOfThronesApi
){

    /**
     * @return a paged list of houses in Game of Thrones.
     * @param page the page number to be fetched.
     */
    suspend fun getHouses(page: Int) = api.getHouses(page)

}