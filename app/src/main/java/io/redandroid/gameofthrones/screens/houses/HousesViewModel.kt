package io.redandroid.gameofthrones.screens.houses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.data.common.ResourceStatus
import io.redandroid.data.repositories.HouseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val housesRepository: HouseRepository
) : ViewModel() {

    private val _housesUiState = MutableStateFlow(HousesUiState())
    val housesUiState = _housesUiState.asStateFlow()

    init {
        getHouses()
    }

    /**
     * Calls the nth [page] of houses from the API and updates
     * [_housesUiState] accordingly.
     */
    private fun getHouses() {
        viewModelScope.launch {
            housesRepository
                .getHouses(1)
                .stateIn(viewModelScope)
                .collectLatest { resource ->
                    _housesUiState.update { currentState ->
                        when (resource.status) {

                            ResourceStatus.SUCCESS -> HousesUiState(
                                resource.data!!,
                                false
                            )

                            ResourceStatus.ERROR -> HousesUiState(
                                currentState.houses,
                                false,
                                resource.message ?: "An unknown error occurred."
                            )

                            ResourceStatus.LOADING -> HousesUiState(
                                currentState.houses,
                                true
                            )

                        }
                    }
                }
        }
    }
}