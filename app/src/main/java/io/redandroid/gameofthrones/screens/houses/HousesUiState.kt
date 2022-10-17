package io.redandroid.gameofthrones.screens.houses

import androidx.paging.PagingData
import io.redandroid.data.model.House

/**
 * This class describes the immutable UI state of [HousesFragment].
 */
data class HousesUiState (

    /**
     * The main content of the houses screen.
     */
    val houses : PagingData<House>? = null,

    /**
     * If not empty, the UI should enter an error state and show this message.
     * If empty, everything works as expected.
     */
    val errorMessage: String = ""
)
