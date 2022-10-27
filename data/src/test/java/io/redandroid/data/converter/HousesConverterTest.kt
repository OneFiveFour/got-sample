package io.redandroid.data.converter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.redandroid.data.utils.FileUtil
import org.junit.Test
import io.redandroid.network.model.Houses as NetworkHouses

class HousesConverterTest {

    private val sut = HousesConverter

    @Test
    fun `convert list of houses from network into a list of domain houses`() {
        // Given a network House model
        val gson = Gson()
        val json = FileUtil.getJson("houses.json")
        val houses = gson.fromJson(json, NetworkHouses::class.java)

        // When converting this model
        val convertedHouses = sut.convert(houses)

        // Then expect it to have the correct number of items
        assertThat(convertedHouses.size).isEqualTo(29)
    }

    @Test(expected = NullPointerException::class)
    fun `empty json input throws null pointer exception`() {
        // Given a network House model
        val gson = Gson()
        val json = FileUtil.getJson("empty.json")
        val houses = gson.fromJson(json, NetworkHouses::class.java)

        // When converting this model expect an NPE
        sut.convert(houses)
    }

}