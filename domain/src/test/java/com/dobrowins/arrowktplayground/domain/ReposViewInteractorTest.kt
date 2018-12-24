package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
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
			`when`(githubRepositoryMock.loadRepositoriesById(""))
				.thenReturn(IO.just(emptyList()))
			`when`(githubRepositoryMock.cache(emptyList()))
				.thenReturn(emptyList())
			reposViewInteractor.fetchReposData("").unsafeRunSync()
			verify(githubRepositoryMock, times(1)).cache(emptyList())
		}
	}

}