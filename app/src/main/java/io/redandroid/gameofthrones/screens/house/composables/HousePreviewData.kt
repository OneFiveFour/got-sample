package io.redandroid.gameofthrones.screens.house.composables

import io.redandroid.data.model.House
import io.redandroid.data.model.Person
import io.redandroid.data.model.Region

fun getHousePreview() = House(
    1,
    "Test House",
    Region.STORMLANDS,
    "No Foe May Pass",
    "A golden wreath, on a blue field with a gold border(Azure, a garland of laurel within a bordure or)",
    Person("Name of Lord", listOf("Title 1", "Title 2")),
    listOf(
        Person("Name of Sworn Member 1", listOf("Title 1", "Title 2")),
        Person("Name of Sworn Member 2", emptyList()),
        Person("Name of Sworn Member 3", listOf("Title 1"))
    )
)