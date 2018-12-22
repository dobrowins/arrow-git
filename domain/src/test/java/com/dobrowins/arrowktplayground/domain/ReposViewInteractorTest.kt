package com.dobrowins.arrowktplayground.domain

import arrow.core.Try
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.IOException
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class ReposViewInteractorTest : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Default

    private lateinit var reposViewInteractor: ReposViewInteractor
    private lateinit var githubRepositoryMock: GitHubRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        githubRepositoryMock = mock(GitHubRepository::class.java)
        reposViewInteractor = ReposViewInteractorImpl(
            githubRepositoryMock
        )
    }

    @Test
    fun `fetchReposData returns left either if fetching went wrong`() {
        `when`(githubRepositoryMock.loadRepositoriesById(anyString()))
            .thenReturn(Try.Failure(IOException()))
        runBlocking {
            reposViewInteractor.fetchReposData(anyString()).runCatching {
                assertTrue(this.isLeft())
            }.getOrThrow()
        }
    }


    @Test
    fun `fetchReposData triggers caching of mapped response and returns right either if fetching were successful`() {
        `when`(githubRepositoryMock.loadRepositoriesById(anyString()))
            .thenReturn(Try.Success(emptyList()))
        runBlocking {
            reposViewInteractor.fetchReposData(anyString()).runCatching {
                assertTrue(this.isRight())
            }.getOrThrow()
        }
        verify(githubRepositoryMock, times(1)).cache(anyList())
    }

}