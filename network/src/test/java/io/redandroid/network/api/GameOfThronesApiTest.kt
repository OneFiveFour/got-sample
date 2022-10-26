package io.redandroid.network.api

import com.google.common.truth.Truth.assertThat
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import io.redandroid.network.di.NetworkModule
import io.redandroid.network.rules.MockWebServerRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


@OptIn(ExperimentalCoroutinesApi::class)
class GameOfThronesApiTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    lateinit var sut: GameOfThronesApi

    @Before
    fun setup() {
        val module = NetworkModule()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServerRule.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

        sut = module.provideGameOfThronesApi(retrofit)
    }

    @Test
    fun `getHouses with server response 200 returns success body`() = runTest {
        // Given a correct response
        val responseFile = "getHouses_200.json"
        val responseCode = HttpURLConnection.HTTP_OK
        mockWebServerRule.mockHttpResponse(responseFile, responseCode)

        // When method is called
        val housesResponse = sut.getHouses(1)

        // Then expect a success response from the network
        assertThat(housesResponse is NetworkResponse.Success).isTrue()
        assertThat((housesResponse as NetworkResponse.Success).response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        assertThat(housesResponse.body.size).isEqualTo(50)
    }

    @Test
    fun `getHouses with server response 500 returns error body`() = runTest {
        // Given a correct response
        val responseFile = "getHouses_500.json"
        val responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
        mockWebServerRule.mockHttpResponse(responseFile, responseCode)

        // When method is called
        val housesResponse = sut.getHouses(1)

        // Then expect a success response from the network
        assertThat(housesResponse).isInstanceOf(NetworkResponse.UnknownError::class.java)
        assertThat((housesResponse as NetworkResponse.UnknownError).code).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)
        assertThat(housesResponse.body?.message).isNull()
    }

    @Test
    fun `getPerson with server response 200 returns success body`() = runTest {
        // Given a correct response
        val responseFile = "getPerson_200.json"
        val responseCode = HttpURLConnection.HTTP_OK
        mockWebServerRule.mockHttpResponse(responseFile, responseCode)

        // When method is called
        val personResponse = sut.getPerson(1)

        // Then expect a success response from the network
        assertThat(personResponse is NetworkResponse.Success).isTrue()
        assertThat((personResponse as NetworkResponse.Success).response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        assertThat(personResponse.body.name).isEqualTo("Delonne Allyrion")
    }

    @Test
    fun `getPerson with server response 404 returns error body`() = runTest {
        // Given a correct response
        val responseFile = "getPerson_404.json"
        val responseCode = HttpURLConnection.HTTP_NOT_FOUND
        mockWebServerRule.mockHttpResponse(responseFile, responseCode)

        // When method is called
        val personResponse = sut.getPerson(1)

        // Then expect a success response from the network
        assertThat(personResponse).isInstanceOf(NetworkResponse.UnknownError::class.java)
        assertThat((personResponse as NetworkResponse.UnknownError).code).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
        assertThat(personResponse.body?.message).isNull()
    }
}


