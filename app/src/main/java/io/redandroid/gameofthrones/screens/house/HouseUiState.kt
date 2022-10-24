package io.redandroid.gameofthrones.screens.house

import io.redandroid.data.model.House
import io.redandroid.data.model.Region

data class HouseUiState(

    /**
     * The data of the selected house.
     */
    val house: House = House(0, "", Region.UNKNOWN, "", ""),

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
