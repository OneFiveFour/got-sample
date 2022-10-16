package io.redandroid.gameofthrones.screens.houses

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.redandroid.data.model.House
import io.redandroid.data.repositories.HouseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This ViewModel provides data related to houses in Game of Thrones.
 */
@HiltViewModel
class HousesViewModel @Inject constructor(
    private val housesRepository: HouseRepository
) : ViewModel() {

    /**
     * @return a Flow holding PagingData of the houses in Game of Thrones.
     */
    fun getHouses(): Flow<PagingData<House>> {
        return housesRepository.getHouses().map { pagingData ->
            pagingData.map { house -> house }
        }
    }
}