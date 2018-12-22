package com.dobrowins.arrowktplayground.repository.cache

import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorker @Inject constructor() : PersistWorker() {

	private val bookName = "github response cache"
	private val keyReposCache = "github response repos cache"

    // TODO: if cache is present -> delete!
    fun put(repos: List<RepositoryData>) =
        put(keyReposCache, repos, bookName)

    fun getRepositoryFromCache(repositoryName: String?): RepositoryData? =
		getAllRepositoriesFromCacheOrNull()
			?.filterNotNull()
            ?.first { it.fullName == repositoryName }

	val clearCache: () -> Boolean = {
		deleteByKey(keyReposCache, bookName)
		get(keyReposCache, null, bookName) == null
	}

    private val getAllRepositoriesFromCacheOrNull: () -> List<RepositoryData?>? = {
        get<List<RepositoryData?>?>(keyReposCache, null, bookName)
	}

}
