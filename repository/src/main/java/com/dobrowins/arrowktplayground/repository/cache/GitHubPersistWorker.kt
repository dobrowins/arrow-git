package com.dobrowins.arrowktplayground.repository.cache

import arrow.effects.IO
import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorker @Inject constructor() : PersistWorker() {

	private val bookName = "github response cache"
	private val keyReposCache = "github response repos cache"
	private val ioScope = CoroutineScope(Dispatchers.IO)

	fun put(repos: List<RepositoryDataResponse>) {
		ioScope.launch {
			put(keyReposCache, repos, bookName)
		}
	}

	fun getRepository(repositoryId: String?): IO<RepositoryDataResponse> = TODO()

}