package io.redandroid.data.fakes

import io.redandroid.data.model.House
import io.redandroid.data.model.Region

/**
 * Object that provides Fakes of the data class [House]
 */
object HouseFakes {

    /**
     * @return a standard House with a lord and 1 sworn member
     */
    fun getHouse() = House(
        id = 1,
        name = "Test House",
        region = Region.REACH,
        coatOfArms = "Coat Of Arms Text",
        swornMembers = listOf(PersonFakes.getPerson()),
        currentLord = PersonFakes.getPerson(),
        words = "Words of this house"
    )

}