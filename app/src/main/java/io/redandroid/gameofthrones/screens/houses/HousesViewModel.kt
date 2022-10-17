package io.redandroid.gameofthrones.screens.houses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.data.repositories.HouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * This ViewModel provides data related to houses in Game of Thrones.
 */
@HiltViewModel
class HousesViewModel @Inject constructor(
    private val housesRepository: HouseRepository
) : ViewModel() {

    private val _housesUiState = MutableStateFlow(HousesUiState())
    val housesUiState: StateFlow<HousesUiState> = _housesUiState

    init {
        getHouses()
    }

    /**
     * Starts loading the paged data of houses and
     * updates the UI State accordingly.
     */
    private fun getHouses() = viewModelScope.launch {
        housesRepository.getHouses()
            .cachedIn(viewModelScope)
            .collectLatest { pagingData ->
                _housesUiState.value = HousesUiState(houses = pagingData)
            }
    }

    /**
     * Checks the given [states] for errors and
     * updates the UI State accordingly.
     */
    fun checkForErrors(states: CombinedLoadStates) {
        val refreshStateMediator = states.mediator?.refresh
        val appendStateMediator = states.mediator?.append
        val refreshState = states.refresh
        val appendState = states.append

        val errorMessage = when {
            appendStateMediator is LoadState.Error -> appendStateMediator.error.message
            refreshStateMediator is LoadState.Error -> refreshStateMediator.error.message
            appendState is LoadState.Error -> appendState.error.message
            refreshState is LoadState.Error -> refreshState.error.message
            else -> ""
        } ?: ""

        if (errorMessage.isNotEmpty()) {
            _housesUiState.value = HousesUiState(errorMessage = errorMessage)
        }
    }
}