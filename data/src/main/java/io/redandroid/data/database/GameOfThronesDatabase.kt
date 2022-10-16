package io.redandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.redandroid.data.model.House
import io.redandroid.data.model.HousePagingKey

/**
 * The main database of this app.
 */
@Database(
    entities = [House::class, HousePagingKey::class],
    version = 1,
    exportSchema = false
)
abstract class GameOfThronesDatabase: RoomDatabase() {

    /**
     * get a handle on the [HouseDao]
     */
    abstract fun houseDao(): HouseDao

    /**
     * get a handle on the [HousePagingKeyDao]
     */
    abstract fun housePagingKeyDao(): HousePagingKeyDao

}