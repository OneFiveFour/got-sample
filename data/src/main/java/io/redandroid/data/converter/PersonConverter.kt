package io.redandroid.data.converter

import io.redandroid.data.model.Person as AppPerson
import io.redandroid.network.model.Person as NetworkPerson

/**
 * Used to convert a [NetworkPerson] into an [AppPerson]
 */
object PersonConverter {

    /**
     * @return an [AppPerson] converted from the given [input]
     */
    fun convert(input: NetworkPerson) :  AppPerson{

        val name = input.name
        val titles = when {
            input.titles.isEmpty() -> emptyList()
            input.titles.size == 1 && input.titles.first() == "" -> emptyList()
            else -> input.titles
        }

        return AppPerson(
            name,
            titles
        )

    }

}
