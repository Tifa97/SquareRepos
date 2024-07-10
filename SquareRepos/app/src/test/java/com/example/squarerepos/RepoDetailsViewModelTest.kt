package com.example.squarerepos

import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.repository.BackendRepository
import com.example.squarerepos.util.Resource
import com.example.squarerepos.viewmodel.RepoDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RepoDetailsViewModelTest {
    // Set the main coroutines dispatcher for unit testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var backendRepository: BackendRepository

    @Before
    fun setup() {
        // Initialize MockRepository and ViewModel
        backendRepository = mock(BackendRepository::class.java)
        viewModel = RepoDetailsViewModel(backendRepository)
    }

    @Test
    fun whenIsSuccessfull_getRepoByName_updatesRepo() = runTest {
        val mockRepoItem = ReposResponseItem(
            id = 1,
            name = "test",
            description = "test description",
            full_name = "square/test",
            html_url = "http://something.com",
            language = "C#",
            owner = null,
            private = true
        )

        `when`(backendRepository.getRepoByName(Mockito.anyString())).thenReturn(Resource.Success(mockRepoItem))

        viewModel.getRepoByName("repoName")
        delay(100)

        assertEquals(mockRepoItem, viewModel.repo.value)
    }

    @Test
    fun whenIsNotSuccessfull_getRepoByName_updatesLoadError() = runTest {
        val errorMessage = "Test error"
        `when`(backendRepository.getRepoByName(Mockito.anyString())).thenReturn(Resource.Error(errorMessage))

        viewModel.getRepoByName("repoName")
        delay(100)

        assertEquals(errorMessage, viewModel.loadError.value)
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}