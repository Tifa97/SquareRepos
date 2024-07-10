package com.example.squarerepos

import com.example.squarerepos.remote.SquareApiInterface
import com.example.squarerepos.remote.response.ReposResponse
import com.example.squarerepos.remote.response.ReposResponseItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit

class SquareApiClientTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: SquareApiInterface

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val json = Json { ignoreUnknownKeys = true }
        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/")) // Use mockWebServer URL
            .client(client)
            .build()

        service = retrofit.create(SquareApiInterface::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun whenResponseIsSuccess_getRepos_mapsParametersCorrectly() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "[{\"id\": 1, \"name\": \"Item 1\", \"description\": \"mockItem\", \"full_name\": \"Item 1\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}," +
                        " {\"id\": 2, \"name\": \"Item 2\", \"description\": \"mockItem\", \"full_name\": \"Item 1\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}]"
            )
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val items = service.getRepos()
            assertEquals(2, items.size)
            assertEquals("Item 1", items[0].name)
            assertEquals("Item 2", items[1].name)
            // We can add assertions for each of the parameters of the items to fully check this
        }
    }

    @Test
    fun whenResponseIsSuccess_getRepoByName_mapsParametersCorrectly() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "{\"id\": 1, \"name\": \"Item\", \"description\": \"mockItem\", \"full_name\": \"square/Item\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}"
            )
        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val item = service.getRepoByName("Item")
            assertEquals(1, item.id)
            assertEquals("Item", item.name)
            assertEquals("square/Item", item.full_name)
            // We can add assertions for each of the parameters of the item to fully check this
        }
    }
}