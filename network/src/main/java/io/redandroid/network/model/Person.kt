package io.redandroid.network.model
import com.google.gson.annotations.SerializedName

/**
 * A person in Game Of Thrones
 */
data class Person(
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("allegiances")
    val allegiances: List<String>,
    @SerializedName("books")
    val books: List<String>,
    @SerializedName("born")
    val born: String,
    @SerializedName("culture")
    val culture: String,
    @SerializedName("died")
    val died: String,
    @SerializedName("father")
    val father: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("mother")
    val mother: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playedBy")
    val playedBy: List<String>,
    @SerializedName("povBooks")
    val povBooks: List<Any>,
    @SerializedName("spouse")
    val spouse: String,
    @SerializedName("titles")
    val titles: List<String>,
    @SerializedName("tvSeries")
    val tvSeries: List<String>,
    @SerializedName("url")
    val url: String
)