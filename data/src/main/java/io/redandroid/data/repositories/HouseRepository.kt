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
import io.redandroid.data.paging.HousesRemoteMediator
import io.redandroid.network.api.HouseService
import io.redandroid.network.api.PersonService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import io.redandroid.data.model.House as AppHouse
import io.redandroid.network.model.House as NetworkHouse

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

    /**
     * Loads a single house with the given [id] and returns it as Flow.
     * The data is wrapped in a [Resource] that indicates the current loading status.
     */
    suspend fun getHouse(id: Int) = networkBoundResource(
        networkCall = { houseService.getHouse(id) },
        convertNetworkResponse = { networkResponse ->
            var convertedHouse = HouseConverter.convert(networkResponse)
            convertedHouse = getCurrentLord(networkResponse, convertedHouse)
            convertedHouse = getSwornMembers(networkResponse, convertedHouse)
            convertedHouse
        },
        storeInDatabase = { convertedItems -> houseDao.insert(convertedItems) },
        fetchFromDatabase = { houseDao.get(id) }
    )

    /**
     * Using the [networkResponse], this method loads all sworn members of the house.
     * These members are then added to the [convertedHouse] and the new house data is returned.
     */
    private suspend fun getSwornMembers(networkResponse: NetworkHouse, convertedHouse: AppHouse): AppHouse {

        // To allow parallel loading of the list of sworn members, we are using a Deferred object
        val membersDeferred = mutableListOf<Deferred<Person>>()

        coroutineScope {
            networkResponse.swornMembers.forEach { memberUrl ->
                val memberDeferred = async {

                    // load all sworn members in parallel and add the result to membersDeferred

                    val memberIdString = memberUrl.substringAfterLast("/")
                    if (memberIdString.isEmpty()) return@async Person("", emptyList())

                    val memberId = Integer.parseInt(memberIdString)
                    val memberNetworkResponse = personService.getPerson(memberId)
                    if (memberNetworkResponse is NetworkResponse.Success) {
                        return@async PersonConverter.convert(memberNetworkResponse.body)
                    }

                    return@async Person("", emptyList())
                }

                membersDeferred.add(memberDeferred)
            }
        }

        // after all deferrals arrived, we return them along with the convertedHouse data object.
        val members = membersDeferred.awaitAll().filter { it.name.isNotEmpty() }
        return convertedHouse.copy(swornMembers = members)
    }

    /**
     * Using the [networkResponse], this method loads the current lord of the house.
     * The lord data is then added to the [convertedHouse] and the new house data is returned.
     */
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


    /**
     * @return a Pager object that can be used to show the paged data in a RecyclerView.
     */
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
