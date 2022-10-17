package io.redandroid.gameofthrones.screens.houses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.data.model.House
import io.redandroid.data.model.HouseListItem
import io.redandroid.data.model.ListHeader
import io.redandroid.data.repositories.HouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
            .map { pagingData ->
                pagingData.insertSeparators { before, after ->
                    return@insertSeparators insertListHeader(after, before)
                }

            }
            .cachedIn(viewModelScope)
            .collectLatest { pagingData ->
                _housesUiState.value = HousesUiState(houses = pagingData)
            }
    }

    /**
     * Uses [after] and [before] to check if a new starting letter came up.
     * If so, a list header is inserted.
     **/
    private fun insertListHeader(after: HouseListItem?, before: HouseListItem?): ListHeader? {
        // End of list or not a House -> no separator
        if (after == null || after !is House) {
            return null
        }

        val firstCharAfter = after.name.first().toString()

        if (before == null) {
            // we are the top of the list -> insert (first) header
            return ListHeader(firstCharAfter)
        }

        if (before !is House) {
            // before is not a house. Should never happen here.
            return null
        }

        val firstCharBefore = before.name.first().toString()

        if (firstCharAfter != firstCharBefore) {
            // before has a different starting char than after -> insert (new) header
            return ListHeader(firstCharAfter)
        }

        return null
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