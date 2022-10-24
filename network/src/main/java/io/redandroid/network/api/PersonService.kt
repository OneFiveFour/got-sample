package io.redandroid.network.api

import javax.inject.Inject

/**
 * This interface describes all public calls regarding characters in Game of Thrones.
 */
class PersonService @Inject constructor(
    private val api: GameOfThronesApi
) {

    /**
     * @return the character with the given [id].
     */
    suspend fun getPerson(id: Int) = api.getPerson(id)

}