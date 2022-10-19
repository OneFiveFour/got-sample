package io.redandroid.utils

import java.io.File

internal object FileUtil {

    fun getJson(fileName: String): String {
        val uri = this.javaClass.classLoader?.getResource(fileName)
            ?: throw NullPointerException("cannot getJson. ClassLoader is null.")
        val file = File(uri.path)
        return String(file.readBytes())
    }

}