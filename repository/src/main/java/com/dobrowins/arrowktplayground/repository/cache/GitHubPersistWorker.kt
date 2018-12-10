package com.dobrowins.arrowktplayground.repository.cache

import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorker @Inject constructor() : PersistWorker() {

	private val bookName = "github response cache"
	private val keyReposCache = "github response repos cache"

    // TODO: if cache is present -> delete!
    fun put(repos: List<RepositoryDataResponse>) =
        put(keyReposCache, repos, bookName)

	fun getRepositoryFromCache(repositoryName: String?): RepositoryDataResponse? =
		getAllRepositoriesFromCacheOrNull()
			?.filterNotNull()
			?.first { it.full_name == repositoryName }

	val clearCache: () -> Boolean = {
		deleteByKey(keyReposCache, bookName)
		get(keyReposCache, null, bookName) == null
	}

	private val getAllRepositoriesFromCacheOrNull: () -> List<RepositoryDataResponse?>? = {
		get<List<RepositoryDataResponse?>?>(keyReposCache, null, bookName)
	}

}
