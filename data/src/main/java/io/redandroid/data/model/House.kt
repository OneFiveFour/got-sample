package io.redandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A house in Game of Thrones.
 */
@Entity(tableName = "customField")
data class House(
    @PrimaryKey
    val id: Int,
    val name: String
)
