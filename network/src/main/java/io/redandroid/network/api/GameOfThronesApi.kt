package io.redandroid.network.api

import com.haroldadmin.cnradapter.NetworkResponse
import io.redandroid.network.model.ErrorResponse
import io.redandroid.network.model.House
import io.redandroid.network.model.Houses
import io.redandroid.network.model.Person
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The Retrofit definition of the Game of Thrones API.
 */
interface GameOfThronesApi {

    companion object {
        const val BASE_URL = "https://www.anapioficeandfire.com/api/"
    }

    /**
     * @return a paged list of houses in Game of Thrones.
     * @param page the page number to fetch. If null, the first page will load.
     */
    @GET("houses?pageSize=50")
    suspend fun getHouses(@Query("page") page: Int?): NetworkResponse<Houses, ErrorResponse>

    /**
     * @return the house with the given [id].
     */
    @GET("houses/{id}")
    suspend fun getHouse(@Path("id") id: Int): NetworkResponse<House, ErrorResponse>


    /**
     * @return the character with the given [id]
     */
    @GET("characters/{id}")
    suspend fun getPerson(@Path("id") id: Int) :NetworkResponse<Person, ErrorResponse>

}