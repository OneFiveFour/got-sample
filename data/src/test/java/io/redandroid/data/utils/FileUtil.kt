package io.redandroid.data.utils

import java.io.File

internal object FileUtil {

    /**
     * Loads the json with the given [fileName] from the resources directory.
     */
    fun getJson(fileName: String): String {
        val uri = this.javaClass.classLoader?.getResource(fileName)
            ?: throw NullPointerException("cannot getJson. ClassLoader is null.")
        val file = File(uri.path)
        return String(file.readBytes())
    }

}