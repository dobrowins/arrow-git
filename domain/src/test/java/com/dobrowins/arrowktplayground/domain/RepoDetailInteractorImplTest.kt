package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class RepoDetailInteractorImplTest {

    private lateinit var githubRepositoryMock: GitHubRepository
    private lateinit var repoDetailInteractor: RepoDetailInteractor

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        githubRepositoryMock = mock(GitHubRepository::class.java)
        repoDetailInteractor = RepoDetailInteractorImpl(
            githubRepositoryMock
        )
    }

    @Test
    fun `getRepoData returns null if repositoryId is null`() {
        runBlocking {
            `when`(githubRepositoryMock.getRepositoryFromCache(null))
                .thenReturn(IO.just(null))
            val repo = repoDetailInteractor.getRepoData(null).unsafeRunSync()
            assertNull(repo)
        }
    }

    @Test
    fun `getRepoData returns cached data`() {
        runBlocking {
            `when`(githubRepositoryMock.getRepositoryFromCache(""))
                .thenReturn(IO.just(GithubRepositoryFixture.repositoryData))
            val repo = repoDetailInteractor.getRepoData("").unsafeRunSync()
            assertNotNull(repo)
        }
    }

}