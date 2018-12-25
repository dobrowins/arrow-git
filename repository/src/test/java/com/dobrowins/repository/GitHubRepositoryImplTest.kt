package com.dobrowins.repository

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.arrowktplayground.repository.api.GithubApiImpl
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class GitHubRepositoryImplTest {

    companion object {
        private const val REPO_FAIL = "fail"
        private const val REPO_SUCCESS = "success"
    }

    private lateinit var repository: GitHubRepositoryImpl
    private val apiImplMock: GithubApiImpl
    private val persistWorkerMock: GitHubPersistWorker
    private val dispatchersMock: DispatchersProvider
    private lateinit var mockWebServer: MockWebServer
    private val supervisorJob = SupervisorJob()
    private val testScope = Dispatchers.Default + supervisorJob

    init {
        MockitoAnnotations.initMocks(this@GitHubRepositoryImplTest)
        apiImplMock = mock(GithubApiImpl::class.java)
        persistWorkerMock = mock(GitHubPersistWorker::class.java)
        dispatchersMock = TestDispatchers.provide()
    }

    @Before
    fun init() {
        repository = GitHubRepositoryImpl(
            apiImplMock,
            persistWorkerMock,
            dispatchersMock
        )
        mockWebServer = MockWebServer()
    }

    @Test
    fun `loadRepositoriesById - returns items if all is well`() {
        `when`(apiImplMock.getUserRepos(REPO_SUCCESS))
            .thenReturn(GithubApiFixture.repositoryDataResponseList)

        runBlocking {
            val list = repository.loadRepositoriesById(REPO_SUCCESS).unsafeRunSync()
            assertNotNull(list, "repository failed to return list of responses")
            verify(apiImplMock, times(1)).getUserRepos(REPO_SUCCESS)
        }
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun `loadRepositoriesById does not handling errors safely`() {
        `when`(apiImplMock.getUserRepos(REPO_FAIL)).thenThrow(RuntimeException())
        runBlocking {
            repository.loadRepositoriesById(REPO_FAIL).unsafeRunSync()
        }
    }

    @Test
    fun `getRepositoryFromCache - returns data if response is cached`() {
        `when`(persistWorkerMock.getRepositoryFromCache(REPO_SUCCESS))
            .thenReturn(GithubApiFixture.repositoryData)

        runBlocking {
            val cached = repository.getRepositoryFromCache(REPO_SUCCESS).unsafeRunSync()
            assertNotNull(cached)
        }
    }

    @Test
    fun `getRepositoryFromCache - returns null if no response is cached`() {
        `when`(persistWorkerMock.getRepositoryFromCache(REPO_FAIL)).thenReturn(null)
        runBlocking {
            val cached = repository.getRepositoryFromCache(REPO_FAIL).unsafeRunSync()
            assertNull(cached)
        }
    }

}