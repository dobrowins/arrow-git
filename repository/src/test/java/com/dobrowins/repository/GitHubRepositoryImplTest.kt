package com.dobrowins.repository

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class GitHubRepositoryImplTest {

    companion object {
        private const val REPO_FAIL = "fail"
        private const val REPO_NULL = "null"
        private const val REPO_SUCCESS = "success"
    }

    private lateinit var repository: GitHubRepositoryImpl
    private val apiMock: GithubApi
    private val persistWorkerMock: GitHubPersistWorker
    private val dispatchersMock: DispatchersProvider

    init {
        MockitoAnnotations.initMocks(this@GitHubRepositoryImplTest)
        apiMock = mock(GithubApi::class.java)
        `when`(apiMock.getUserRepos(REPO_FAIL)).thenAnswer { Exception() }
        `when`(apiMock.getUserRepos(REPO_NULL)).thenReturn(null)
        //`when`(apiMock.getUserRepos(REPO_SUCCESS)).thenReturn(GithubApiFixture.repositoryDataResponseList)
        persistWorkerMock = mock(GitHubPersistWorker::class.java)
        dispatchersMock = object : DispatchersProvider {
            override val main: CoroutineContext = Dispatchers.Default
            override val io: CoroutineContext = Dispatchers.Default
            override val default: CoroutineContext = Dispatchers.Default
        }
    }

    @Before
    fun init() {
        repository = GitHubRepositoryImpl(
            apiMock,
            persistWorkerMock,
            dispatchersMock
        )
    }

    @Test
    fun `loadRepositoriesById - returns IO if all is well`() {
    }

    @Test(expected = Exception::class)
    fun `loadRepositoriesById does not handling errors safely`() {
        repository.loadRepositoriesById(REPO_FAIL).unsafeRunSync()
    }

}