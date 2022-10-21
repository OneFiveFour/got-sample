package io.redandroid.gameofthrones.screens.house

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.data.common.ResourceStatus
import io.redandroid.data.repositories.HouseRepository
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val houseRepository: HouseRepository
) : ViewModel() {


    private val _houseUiState = mutableStateOf(HouseUiState())
    val houseUiState: State<HouseUiState> = _houseUiState

    /**
     * Call this method to set the id of the house to load.
     * The network process and its result can be collected from [houseUiState]
     */
    suspend fun setHouseId(id: Int) {
        houseRepository.getHouse(id).collectLatest { resource ->
            when (resource.status) {
                ResourceStatus.SUCCESS -> {
                    val house = resource.data ?: return@collectLatest
                    _houseUiState.value = HouseUiState(house = house)
                }
                ResourceStatus.ERROR -> _houseUiState.value = HouseUiState(errorMessage = resource.message ?: "Unknown Error")
                ResourceStatus.LOADING -> _houseUiState.value = HouseUiState(isLoading = true)
            }
        }
    }

}