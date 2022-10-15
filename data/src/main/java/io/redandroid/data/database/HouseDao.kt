package io.redandroid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.redandroid.data.model.House

/**
 * An interface describing all possible database operations for the [House] table
 */
@Dao
interface HouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customFields: List<House>)

    @Query("SELECT * FROM customField")
    suspend fun getAll() : List<House>

    @Query("DELETE FROM customField")
    suspend fun clear()
    
}