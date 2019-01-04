package com.dobrowins.arrowgit.domain

import arrow.effects.IO
import com.dobrowins.arrowgit.domain.data.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
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

    @Test(expected = RuntimeException::class)
    fun `fetchReposData returns left either if fetching went wrong`() {
        runBlocking {
            `when`(githubRepositoryMock.loadRepositoriesById(anyString()))
                .thenReturn(IO.raiseError(RuntimeException()))
            reposViewInteractor.fetchReposData(anyString()).unsafeRunSync()
        }
    }


    @Test
    fun `fetchReposData triggers caching of mapped response and returns right either if fetching were successful`() {
        runBlocking {
            `when`(githubRepositoryMock.loadRepositoriesById(STRING_PROFILE_NAME))
                .thenReturn(IO.just(emptyList()))
            `when`(githubRepositoryMock.cache(ArgumentMatchers.anyList()))
                .thenReturn(emptyList())
            reposViewInteractor.fetchReposData(STRING_PROFILE_NAME).unsafeRunSync()
        }
    }

    companion object {
        private const val STRING_PROFILE_NAME = "dummy"
    }

}