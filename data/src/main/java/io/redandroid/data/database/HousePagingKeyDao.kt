package io.redandroid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.redandroid.data.model.HousePagingKey

/**
 * An interface describing all possible database operations for the house_paging_key table
 */
@Dao
interface HousePagingKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nextPageKey: HousePagingKey)

    @Query("SELECT nextPageKey FROM house_paging_key WHERE page = :page LIMIT 1")
    suspend fun get(page: Int) : Int?

    @Query("DELETE FROM house_paging_key")
    suspend fun clear()
    
}