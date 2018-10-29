package com.dobrowins.arrowktplayground.repository

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions
import arrow.effects.fix
import arrow.syntax.function.andThen
import arrow.typeclasses.binding
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.domain.data.GitHubRepository
import com.dobrowins.domain.data.RepositoryData
import timber.log.Timber

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl(
    private val githubApi: GithubApi
) : GitHubRepository {

    override fun loadRepositoriesById(userId: String): IO<List<RepositoryData>> =
        ForIO extensions {
            binding {
                githubApi.getUserRepos(userId).fold(returnEmptyList, mapResponse)
            }.fix()
        }

    private val map: (List<RepositoryDataResponse>) -> List<RepositoryData> = { responseList ->
        TODO()
    }
    private val cache: (List<RepositoryDataResponse>) -> List<RepositoryDataResponse> = { TODO() }
    private val mapResponse = cache andThen map

    private val returnEmptyList: (Throwable) -> List<RepositoryData> = { t ->
        Timber.e(t)
        emptyList()
    }

}
