package io.redandroid.data.fakes

import io.redandroid.data.model.Person

object PersonFakes {

    fun getPerson() = Person(
        name = "Person Name",
        titles = listOf(
            "Title 1",
            "Title 2"
        )
    )
}