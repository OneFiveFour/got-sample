package io.redandroid.data.common

import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.network.model.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * use this function to make API calls that are then converted into the [ResultType].
 * All network events are emitted as [Resource] events from the created flow.
 */
fun <RequestType, ResultType> networkBoundResource(

    networkCall: suspend () -> NetworkResponse<RequestType, ErrorResponse>,
    convertNetworkResponse: suspend (RequestType) -> ResultType,
    storeInDatabase: suspend (ResultType) -> Unit = { },
    fetchFromDatabase: suspend () -> ResultType? = { null }

) = flow<Resource<ResultType>> {

    when (val serverResponse = networkCall()) {

        is NetworkResponse.Success -> {
            val convertedResponse = convertNetworkResponse(serverResponse.body)

            storeInDatabase(convertedResponse)
            emit(Resource.success(fetchFromDatabase()))
        }

        is NetworkResponse.UnknownError -> {
            val errorMessage = "${serverResponse.response?.message()} | ${serverResponse.response?.code()}"
            emit(Resource.error("An UnknownError occurred while fetching data! $errorMessage", null))
        }

        is NetworkResponse.ServerError -> {
            val errorMessage = "${serverResponse.response?.message()} | ${serverResponse.response?.code()}"
            emit(Resource.error("An ServerError occurred while fetching data! $errorMessage", null))
        }

        is NetworkResponse.NetworkError -> {
            val errorMessage = "${serverResponse.error.message}}"
            emit(Resource.error("An NetworkError occurred while fetching data! $errorMessage", null))
        }
    }

}.catch { exception ->
    emit(Resource.error("An error occurred while fetching data! $exception", null))

}.flowOn(Dispatchers.IO)