package com.dobrowins.arrowktplayground.repository

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions
import arrow.effects.fix
import arrow.syntax.function.forwardCompose
import arrow.typeclasses.binding
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val gitHubPersistWorker: GitHubPersistWorker
) : GitHubRepository {

    override fun loadRepositoriesById(userId: String): IO<List<RepositoryData>> =
        ForIO extensions {
            binding {
                githubApi.getUserRepos(userId)
                    .fold(
                        ifLeft = returnEmptyList,
                        ifRight = cache forwardCompose map
                    )
            }.fix()
        }

    private val returnEmptyList: (Throwable) -> List<RepositoryData> = {
        Timber.e(it)
        emptyList()
    }

    private val cache: (List<RepositoryDataResponse?>) -> List<RepositoryDataResponse> =
        { responseList ->
            val filtered = responseList.filterNotNull()
            gitHubPersistWorker.put(filtered)
            filtered
        }

    private val map: (List<RepositoryDataResponse>) -> List<RepositoryData> = { responseList ->
        responseList.map { repository ->
            RepositoryData(
                id = repository.id?.toInt() ?: 0,
                name = repository.name.orEmpty(),
                fullName = repository.full_name.orEmpty(),
                htmlUrl = repository.html_url.orEmpty(),
                description = repository.description.orEmpty()
            )
        }
    }

}
