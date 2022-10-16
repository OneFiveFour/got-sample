package io.redandroid.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.redandroid.data.database.HouseDao
import io.redandroid.data.model.House
import io.redandroid.data.paging.HousesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This repository is used to fetch houses of Game of Thrones from the GoT API.
 */
class HouseRepository @Inject constructor(
    private val houseDao: HouseDao,
    private val housesRemoteMediator: HousesRemoteMediator
) {

    companion object {
        private const val PAGE_SIZE = 50
    }

//    /**
//     * The houses are retrieved from the GameOfThrones API and stored in a Room database.
//     * By doing so, we have cached information about visible items in the app.
//     * This prevents UI flickering when returning to these screens, as the data is taken from the database instead of
//     * the slow api calls. The database is kept up-to-date by the API call. Changes in the database will automatically
//     * passed on to the UI using Flow.
//     */
//    suspend fun getHouses(page: Int) = networkBoundResource(
//        networkCall = { housesService.getHouses(page) },
//        convertNetworkResponse = { networkResponse -> HousesConverter.convert(networkResponse) },
//        storeInDatabase = { convertedItems -> database.houseDao().insert(convertedItems) },
//        fetchFromDatabase = { database.houseDao().getAll() }
//    )

    @OptIn(ExperimentalPagingApi::class)
    fun getHouses() : Flow<PagingData<House>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = housesRemoteMediator,
            pagingSourceFactory = { houseDao.getAll() }
        ).flow
    }

}
