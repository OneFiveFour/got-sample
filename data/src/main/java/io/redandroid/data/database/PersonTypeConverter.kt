package io.redandroid.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.redandroid.data.model.Person
import java.lang.reflect.Type


/**
 * Database Converter to convert a [Person] to String and back
 */
class PersonTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToPerson(string: String): Person {
        return gson.fromJson(string, Person::class.java)
    }

    @TypeConverter
    fun personToString(person: Person): String {
        return gson.toJson(person)
    }

    @TypeConverter
    fun stringToListOfPerson(string: String): List<Person> {
        val listType: Type = object : TypeToken<ArrayList<Person>>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun listOfPersonToString(personList: List<Person>): String {
        return gson.toJson(personList)
    }

}