package com.dobrowins.arrowktplayground.repository

import arrow.core.Try
import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.api.GithubApiImpl
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl @Inject constructor(
    private val githubApiImpl: GithubApiImpl,
    private val gitHubPersistWorker: GitHubPersistWorker,
    dispatchersProvider: DispatchersProvider
) : GitHubRepository {

    private val ioScope = dispatchersProvider.io

    override fun loadRepositoriesById(profileName: String): Try<List<RepositoryData?>?> =
        Try {
            getUserReposUnsafe(profileName)
                ?.filterNotNull()
                ?.map(mapToRepositoryData)
        }

    override fun getRepositoryFromCache(repositoryId: String?): IO<RepositoryData?> =
        IO.invoke(ioScope) { gitHubPersistWorker.getRepositoryFromCache(repositoryId) }

    override fun cache(responseList: List<RepositoryData?>?): List<RepositoryData> =
        responseList?.filterNotNull()?.let { list ->
            gitHubPersistWorker.put(list)
            list
        } ?: emptyList()

    private val getUserReposUnsafe: (String) -> List<RepositoryDataResponse?>? =
        githubApiImpl::getUserRepos

    private val mapToRepositoryData: (RepositoryDataResponse?) -> RepositoryData? = { data ->
        if (data == null) data
        else RepositoryData(
            id = data.id,
            name = data.name,
            fullName = data.full_name,
            htmlUrl = data.html_url,
            description = data.description,
            language = data.language,
            forkedCount = data.forks,
            starredCount = data.stargazers_count
        )
    }

}
