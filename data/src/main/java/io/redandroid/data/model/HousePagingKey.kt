package io.redandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This database table stores the page key for the upcoming page
 * of house data to fetch from the api.
 */
@Entity(tableName = "house_paging_key")
data class HousePagingKey(
    @PrimaryKey
    val page: Int,
    val nextPageKey: Int
)
