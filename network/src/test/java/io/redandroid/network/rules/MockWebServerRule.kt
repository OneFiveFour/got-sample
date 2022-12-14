package io.redandroid.network.rules

import android.util.Log
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.rules.ExternalResource
import java.io.File
import java.io.IOException

/**
 * Add this Rule to make use of [MockWebServer].
 * The Rule starts and terminates the web server before and after each test.
 */
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

    /**
     * set the next response of the MockWebServer.
     *
     * @param fileName the name of the file that should return as response body. Usually a json file. Place this file in the "resources" folder.
     * @param responseCode the HTTP response code that is returned with the next request.
     */
    fun mockHttpResponse(fileName: String, responseCode: Int) {
        val json = getJson(fileName)
        server.enqueue(MockResponse().setResponseCode(responseCode).setBody(json))
    }

    /**
     * Loads the json file with the given [fileName] from the resources directory.
     */
    private fun getJson(fileName: String): String {
        val uri = this.javaClass.classLoader?.getResource(fileName)
            ?: throw NullPointerException("cannot getJson. ClassLoader is null.")
        val file = File(uri.path)
        return String(file.readBytes())
    }
}