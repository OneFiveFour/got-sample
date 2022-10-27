package io.redandroid.data.fakes

import io.redandroid.data.model.House
import io.redandroid.data.model.Person

/**
 * Object that provides Fakes of the data class [Person]
 */
object PersonFakes {

    /**
     * @return a standard person with a name and 2 titles.
     */
    fun getPerson() = Person(
        name = "Person Name",
        titles = listOf(
            "Title 1",
            "Title 2"
        )
    )
}