package io.redandroid.data.fakes

import io.redandroid.data.model.House
import io.redandroid.data.model.Region

object HouseFakes {

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