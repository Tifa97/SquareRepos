package com.example.squarerepos

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.squarerepos.remote.SquareApiClient
import com.example.squarerepos.remote.SquareApiInterface
import com.example.squarerepos.remote.response.ReposResponse
import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.repository.BackendRepository
import com.example.squarerepos.util.Resource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit

@RunWith(RobolectricTestRunner::class)
class BackendRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: BackendRepository
    private lateinit var context: Context

    @Mock
    private lateinit var mockSquareApiClient: SquareApiClient

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val json = Json { ignoreUnknownKeys = true }
        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/")) // Use mockWebServer URL
            .client(client)
            .build()

        val mockRetrofitService = retrofit.create(SquareApiInterface::class.java)
        Mockito.`when`(mockSquareApiClient.retrofitService).thenReturn(mockRetrofitService)

        context = ApplicationProvider.getApplicationContext()
        repository = BackendRepository(mockSquareApiClient, context)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun whenResponseIsSuccessful_getRepos_returnsCorrectResult() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "[{\"id\": 1, \"name\": \"Item 1\", \"description\": \"mockItem\", \"full_name\": \"Item 1\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}," +
                        " {\"id\": 2, \"name\": \"Item 2\", \"description\": \"mockItem\", \"full_name\": \"Item 1\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}]"
            )
        mockWebServer.enqueue(mockResponse)

        val result = repository.getRepos()
        assert(result is Resource.Success)
        assert(result.data is ReposResponse)
        result as Resource.Success
        assertEquals(2, result.data?.size)
        assertEquals("Item 1", result.data!![0].name)
        assertEquals("Item 2", result.data!![1].name)
    }

    @Test
    fun whenResponseIsError_getRepos_returnsResourceError() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val result = repository.getRepos()
        assert(result is Resource.Error)
        result as Resource.Error
        assertNotNull("Error while fetching repos", result.message)
    }

    @Test
    fun whenResponseIsSuccessful_getRepoByName_returnsCorrectResult() = runBlocking {
        val repoName = "mockname"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "{\"id\": 1, \"name\": \"$repoName\", \"description\": \"mockItem\", \"full_name\": \"square/$repoName\", \"html_url\": \"https://mockurl.com\", \"language\": \"C#\", \"private\": \"true\", \"owner\": { \"id\": \"1\", \"login\": \"mockname\", \"type\": \"organisation\", \"avatar_url\": \"https://avatar.com\" }}"
            )
        mockWebServer.enqueue(mockResponse)

        val result = repository.getRepoByName(repoName)
        assert(result is Resource.Success)
        assert(result.data is ReposResponseItem)
        result as Resource.Success
        assertEquals(1, result.data?.id)
        assertEquals(repoName, result.data?.name)
        assertEquals("square/$repoName", result.data?.full_name)
    }

    @Test
    fun whenResponseIsSuccessful_getRepoByName_returnsResourceError() = runBlocking {
        val repoName = "mockname"
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val result = repository.getRepoByName(repoName)
        assert(result is Resource.Error)
        result as Resource.Error
        assertEquals("Error fetching repo with name $repoName", result.message)
    }
}