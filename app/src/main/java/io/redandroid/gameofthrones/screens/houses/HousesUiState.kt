package io.redandroid.gameofthrones.screens.houses

import androidx.paging.PagingData
import io.redandroid.data.model.HouseListItem

/**
 * This class describes the immutable UI state of [HousesFragment].
 */
data class HousesUiState (

    /**
     * The main content of the houses screen.
     */
    val houses : PagingData<HouseListItem>? = null,

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
