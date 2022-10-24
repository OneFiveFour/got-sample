package io.redandroid.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.redandroid.data.model.House

/**
 * An interface describing all possible database operations for the house table
 */
@Dao
interface HouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(houses: List<House>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(house: House)

    @Query("SELECT * FROM house")
    fun getAll() : PagingSource<Int, House>

    @Query("SELECT * FROM house WHERE id = :id LIMIT 1")
    suspend fun get(id: Int) : House

    @Query("DELETE FROM house")
    suspend fun clear()
    
}