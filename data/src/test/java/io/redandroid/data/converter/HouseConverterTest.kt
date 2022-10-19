package io.redandroid.data.converter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.redandroid.utils.FileUtil
import org.junit.Test
import io.redandroid.network.model.House as NetworkHouse

class HouseConverterTest {

    private val sut = HouseConverter

    @Test
    fun `convert house model from network into domain model`() {
        // Given a network House model
        val gson = Gson()
        val json = FileUtil.getJson("house.json")
        val house = gson.fromJson(json, NetworkHouse::class.java)

        // When converting this model
        val convertedHouse = sut.convert(house)

        // Then expect it to have the correct fields
        assertThat(convertedHouse.id).isEqualTo(1)
        assertThat(convertedHouse.name).isEqualTo("Algood")
    }


    // TODO: add negative tests for faulty input data.
}