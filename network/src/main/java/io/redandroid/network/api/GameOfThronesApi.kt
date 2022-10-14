package io.redandroid.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.network.model.ErrorResponse
import io.redandroid.network.model.Houses
import retrofit2.http.GET

/**
 * The Retrofit definition of the Game of Thrones API.
 */
interface GameOfThronesApi {

    companion object {
        const val BASE_URL = "https://www.anapioficeandfire.com/api/"
    }

    /**
     * @return a paged list of houses in Game of Thrones. The hard-coded page size is 20.
     * @param page the page number to fetch.
     */
    @GET("houses?pageSize=20")
    suspend fun getHouses(page: Int): NetworkResponse<Houses, ErrorResponse>

}