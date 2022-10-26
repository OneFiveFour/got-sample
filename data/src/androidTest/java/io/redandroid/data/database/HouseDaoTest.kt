package io.redandroid.data.database

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.redandroid.data.fakes.HouseFakes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HouseDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: GameOfThronesDatabase

    private lateinit var sut: HouseDao

    @Before
    fun createDb() {
        hiltRule.inject()
        sut = database.houseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun insertAndGetHouseFromDb_success() = runTest {
        // Given a database with 1 house
        val house = HouseFakes.getHouse()
        sut.insert(house)

        // When getting this house from the db
        val result = sut.get(house.id)

        // Then its fields should match
        assert(house.id == result.id)
        assert(house.name == result.name)
    }
}
