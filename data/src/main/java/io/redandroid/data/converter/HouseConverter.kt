package io.redandroid.data.converter

import io.redandroid.data.model.Person
import io.redandroid.data.model.House as AppHouse
import io.redandroid.network.model.House as NetworkHouse

/**
 * Used to convert a [NetworkHouse] into an [AppHouse]
 */
object HouseConverter {

    /**
     * @return an [AppHouse] converted from the given [input]
     */
    fun convert(input: NetworkHouse) : AppHouse {

        val id = Integer.parseInt(input.url.substringAfterLast('/'))
        val name = input.name.substringAfter("House ")
        val region = RegionConverter.convert(input.region)
        val words = input.words
        val coatOfArms = input.coatOfArms
        val currentLord = Person("", emptyList())
        val swornMembers = emptyList<Person>()

        return AppHouse(
            id,
            name,
            region,
            words,
            coatOfArms,
            currentLord,
            swornMembers
        )
    }

}
