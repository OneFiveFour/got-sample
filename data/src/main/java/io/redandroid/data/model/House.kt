package io.redandroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A house in Game of Thrones.
 */
@Entity(tableName = "house")
data class House(
    @PrimaryKey
    val id: Int,
    val name: String,
    val region: Region,
    val words: String,
    val coatOfArms: String,
    val currentLord: Person,
    val swornMembers: List<Person>
) : HouseListItem