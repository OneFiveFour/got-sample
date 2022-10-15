package io.redandroid.data.repositories

import io.redandroid.data.common.networkBoundResource
import io.redandroid.data.converter.HousesConverter
import io.redandroid.data.database.GameOfThronesDatabase
import io.redandroid.network.api.HouseService
import javax.inject.Inject

/**
 * This repository is used to fetch houses of Game of Thrones from the GoT API.
 */
class HouseRepository @Inject constructor(
    private val database: GameOfThronesDatabase
) {

    /**
     * The houses are retrieved from the GameOfThrones API and stored in a Room database.
     * By doing so, we have cached information about visible items in the app.
     * This prevents UI flickering when returning to these screens, as the data is taken from the database instead of
     * the slow api calls. The database is kept up-to-date by the API call. Changes in the database will automatically
     * passed on to the UI using Flow.
     */
    suspend fun getHouses(page: Int) = networkBoundResource(
        networkCall = { HouseService().getHouses(page) },
        convertNetworkResponse = { networkResponse -> HousesConverter.convert(networkResponse) },
        storeInDatabase = { convertedItems -> database.houseDao().insert(convertedItems) },
        fetchFromDatabase = { database.houseDao().getAll() }
    )

}
