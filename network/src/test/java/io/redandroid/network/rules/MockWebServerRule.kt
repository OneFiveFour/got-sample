package io.redandroid.network.rules

import android.util.Log
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.rules.ExternalResource
import java.io.File
import java.io.IOException


class MockWebServerRule : ExternalResource() {

    private val server = MockWebServer()

    private var started = false

    @Before
    override fun before() {
        if (started) return
        started = true
        server.start()
    }

    @After
    override fun after() {
        try {
            server.shutdown()
        } catch (e: IOException) {
            Log.w("MockWebServerRule","MockWebServer shutdown failed", e)
        }
    }

    fun getUrl() = server.url("/").url()!!

    fun mockHttpResponse(fileName: String, responseCode: Int) {
        val json = getJson(fileName)
        server.enqueue(MockResponse().setResponseCode(responseCode).setBody(json))
    }

    private fun getJson(fileName: String): String {
        val uri = this.javaClass.classLoader?.getResource(fileName)
            ?: throw NullPointerException("cannot getJson. ClassLoader is null.")
        val file = File(uri.path)
        return String(file.readBytes())
    }
}