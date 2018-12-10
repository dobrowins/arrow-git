package com.dobrowins.repository

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import com.dobrowins.arrowktplayground.repository.api.GithubApiImpl
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

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
    fun `loadRepositoriesById - caches response then returns items if all is well`() {
        `when`(apiImplMock.getUserRepos(REPO_SUCCESS))
            .thenReturn(GithubApiFixture.repositoryDataResponseList)
        `when`(persistWorkerMock.put(ArgumentMatchers.any<List<RepositoryDataResponse>>()))
            .thenReturn(Unit)

        val result = repository.loadRepositoriesById(REPO_SUCCESS).unsafeRunSync()
        assertNotNull(result, "repository failed to return list of responses")
        verify(apiImplMock, times(1)).getUserRepos(REPO_SUCCESS)
        verify(persistWorkerMock, times(1)).put(ArgumentMatchers.anyList())
    }

    @Test(expected = RuntimeException::class)
    fun `loadRepositoriesById does not handling errors safely`() {
        `when`(apiImplMock.getUserRepos(REPO_FAIL)).thenThrow(RuntimeException())
        repository.loadRepositoriesById(REPO_FAIL).unsafeRunSync()
    }

    @Test
    fun `getRepositoryFromCache - returns data if response is cached`() {
        `when`(persistWorkerMock.getRepositoryFromCache(REPO_SUCCESS))
            .thenReturn(GithubApiFixture.repositoryDataResponse)

        repository.getRepositoryFromCache(REPO_SUCCESS).unsafeRunAsync { either ->
            assertNotNull(either)
            assertTrue(either.isRight())
            either.map { assertNotNull(it) }
        }
    }

    @Test
    fun `getRepositoryFromCache - returns null if no response is cached`() {
        `when`(persistWorkerMock.getRepositoryFromCache(REPO_FAIL)).thenReturn(null)

        repository.getRepositoryFromCache(REPO_FAIL).unsafeRunAsync { either ->
            assertNotNull(either)
            assert(either.isRight())
            either.map { assertNull(it) }
        }
    }

}