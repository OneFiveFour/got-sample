package io.redandroid.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.redandroid.data.common.networkBoundResource
import io.redandroid.data.converter.HouseConverter
import io.redandroid.data.database.HouseDao
import io.redandroid.data.model.House
import io.redandroid.data.paging.HousesRemoteMediator
import io.redandroid.network.api.HouseService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This repository is used to fetch houses of Game of Thrones from the GoT API.
 */
class HouseRepository @Inject constructor(
    private val houseService: HouseService,
    private val houseDao: HouseDao,
    private val housesRemoteMediator: HousesRemoteMediator
) {

    companion object {
        private const val PAGE_SIZE = 50
    }

    suspend fun getHouse(id: Int) = networkBoundResource(
        networkCall = { houseService.getHouse(id) },
        convertNetworkResponse = { networkResponse -> HouseConverter.convert(networkResponse) },
        storeInDatabase = { convertedItems -> houseDao.insert(convertedItems) },
        fetchFromDatabase = { houseDao.get(id) }
    )

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
