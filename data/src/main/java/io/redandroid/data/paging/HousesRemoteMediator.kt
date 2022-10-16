package io.redandroid.data.paging

import androidx.paging.*
import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.data.converter.HousesConverter
import io.redandroid.data.database.HouseDao
import io.redandroid.data.database.HousePagingKeyDao
import io.redandroid.data.model.House
import io.redandroid.data.model.HousePagingKey
import io.redandroid.network.api.HouseService
import io.redandroid.network.model.ErrorResponse
import io.redandroid.network.model.Houses
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * This is part of the Jetpack Paging library version 3.0
 *
 * The RemoteMediators responsibility is to fetch the next batch of items from the network, convert them into our app domain models
 * and store them in the database. By doing so, the database becomes our single spot of truth.
 *
 * Together with the PagingSource which is created by Room, the ViewModels can then build and use the Pager instances.
 *
 * @param houseDao used to cache the fetched houses into the local database
 * @param housePagingKeyDao used to remember what pageKey needs to be used next
 * @param houseService the network service to call the api for more houses
 */
@OptIn(ExperimentalPagingApi::class)
class HousesRemoteMediator @Inject constructor(
    private val houseDao: HouseDao,
    private val housePagingKeyDao: HousePagingKeyDao,
    private val houseService: HouseService
) : RemoteMediator<Int, House>() {

    companion object {
        /**
         * Since the pageSize is hardcoded to 50 (also see notes.md),
         * we can use a single id for the database table that gives us the
         * "next" page key.
         */
        private const val PAGE_KEY_ID = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(loadType: LoadType, state: PagingState<Int, House>): MediatorResult {

        try {
            // depending on the loadType, we define the pageKey for the current request
            val pageKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> housePagingKeyDao.get(PAGE_KEY_ID)
            }

            // the actual API call to get the houses of the current page
            // if pageKey is null, Retrofit will not add this GET param to the url.
            // In that case, the API will return the first page.
            val items = houseService.getHouses(page = pageKey)

            // if a refresh was made, delete all cached items from the database
            if (loadType == LoadType.REFRESH) {
                houseDao.clear()
                housePagingKeyDao.clear()
            }

            // convert network response and put it into the database
            val convertedHouses = convertNetworkResponse(items)
            houseDao.insert(convertedHouses)

            // calculate the pageKey for the next page
            val nextPageKey = (pageKey ?: 1).plus(1)

            // insert paging key into database
            val pagingKey = HousePagingKey(PAGE_KEY_ID, nextPageKey)
            housePagingKeyDao.insert(pagingKey)

            // we are done with this page. Return success.
            return MediatorResult.Success(endOfPaginationReached = convertedHouses.isEmpty())

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        } catch (t: Throwable) {
            return MediatorResult.Error(t)
        }
    }

    /**
     * Convert the given network model into a list of app model [House]s.
     */
    private fun convertNetworkResponse(response: NetworkResponse<Houses, ErrorResponse>): List<House> {
        when (response) {

            is NetworkResponse.Success -> {
                val convertedHouses = HousesConverter.convert(response.body)
                Timber.d("+++ Got ${convertedHouses.size} houses.")
                return convertedHouses
            }

            is NetworkResponse.ServerError -> {
                val throwable = response.error ?: Throwable(response.response?.message() ?: "Unknown Server Error")
                throw throwable
            }
            is NetworkResponse.NetworkError -> {
                throw response.error
            }
            is NetworkResponse.UnknownError -> {
                throw response.error
            }
        }
    }

}
