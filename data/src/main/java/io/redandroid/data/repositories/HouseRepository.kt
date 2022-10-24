package io.redandroid.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.data.common.networkBoundResource
import io.redandroid.data.converter.HouseConverter
import io.redandroid.data.converter.PersonConverter
import io.redandroid.data.database.HouseDao
import io.redandroid.data.model.Person
import io.redandroid.data.model.House as AppHouse
import io.redandroid.data.paging.HousesRemoteMediator
import io.redandroid.network.api.HouseService
import io.redandroid.network.api.PersonService
import io.redandroid.network.model.House as NetworkHouse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This repository is used to fetch houses of Game of Thrones from the GoT API.
 */
class HouseRepository @Inject constructor(
    private val houseService: HouseService,
    private val personService: PersonService,
    private val houseDao: HouseDao,
    private val housesRemoteMediator: HousesRemoteMediator
) {

    companion object {
        private const val PAGE_SIZE = 50
    }

    suspend fun getHouse(id: Int) = networkBoundResource(
        networkCall = { houseService.getHouse(id) },
        convertNetworkResponse = { networkResponse ->
            var convertedHouse = HouseConverter.convert(networkResponse)
            convertedHouse = getCurrentLord(networkResponse, convertedHouse)
            convertedHouse
        },
        storeInDatabase = { convertedItems -> houseDao.insert(convertedItems) },
        fetchFromDatabase = { houseDao.get(id) }
    )

    private suspend fun getCurrentLord(networkResponse: NetworkHouse, convertedHouse: AppHouse) : AppHouse {
        val currentLordIdString = networkResponse.currentLord.substringAfterLast("/")

        if (currentLordIdString.isEmpty()) return convertedHouse

        val currentLordId = Integer.parseInt(currentLordIdString)
        val currentLordNetworkResponse = personService.getPerson(currentLordId)
        if (currentLordNetworkResponse is NetworkResponse.Success) {
            val lord = PersonConverter.convert(currentLordNetworkResponse.body)
            return convertedHouse.copy(currentLord = lord)
        }

        return convertedHouse
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getHouses(): Flow<PagingData<AppHouse>> {
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
