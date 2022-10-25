package io.redandroid.gameofthrones.screens.house

import io.redandroid.data.model.House
import io.redandroid.data.model.Person
import io.redandroid.data.model.Region

/**
 * A description of the UI State of the details of a house
 * in Game Of Thrones.
 */
data class HouseUiState(

    /**
     * The data of the selected house.
     */
    val house: House = House(
        0,
        "",
        Region.UNKNOWN,
        "",
        "",
        Person("", emptyList()),
        emptyList()
    ),

    /**
     * If not empty, the UI should enter an error state and show this message.
     * If empty, everything works as expected.
     */
    val errorMessage: String = "",

    /**
     * True if there is currently network activity. False otherwise
     */
    val isLoading : Boolean = false
)
