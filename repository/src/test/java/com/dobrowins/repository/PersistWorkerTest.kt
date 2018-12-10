package com.dobrowins.repository

import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorkerTest {

	private val testBookName = "test book name"
	private val testIntKey = "test int key"
	private lateinit var githubPersistWorker: GitHubPersistWorker

	@Before
	fun init() {
		githubPersistWorker = GitHubPersistWorker()
		// every test assumes that nothing is stored
		githubPersistWorker.clearCache()
	}

	@Test
	fun `put caches data if no data has been stored`() {
	}

	@Test
	fun `put overwrites data if some has been stored previously`() {
	}

	@Test
	fun `getRepositoryFromCache - returns first not null if response is cached`() {
	}

	@Test
	fun `getRepositoryFromCache - returns null if no data has been stored`() {
	}

	@After
	fun destroy() {
	}

}
