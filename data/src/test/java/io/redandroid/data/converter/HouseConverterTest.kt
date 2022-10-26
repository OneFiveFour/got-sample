package io.redandroid.data.converter

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.redandroid.data.utils.FileUtil
import org.junit.Test
import io.redandroid.network.model.House as NetworkHouse

class HouseConverterTest {

    private val sut = HouseConverter

    @Test
    fun `correct house json is converted into domain model class`() {
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

    @Test(expected = NullPointerException::class)
    fun `empty json input throws null pointer exception`() {
        // Given a network House model
        val gson = Gson()
        val json = FileUtil.getJson("empty.json")
        val house = gson.fromJson(json, NetworkHouse::class.java)

        // When converting this model expect an NPE
        sut.convert(house)
    }

}