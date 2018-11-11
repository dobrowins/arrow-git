package com.dobrowins.arrowktplayground.repository

import arrow.Kind
import arrow.core.Try
import arrow.core.right
import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import arrow.effects.monadError
import arrow.effects.typeclasses.Async
import arrow.syntax.function.forwardCompose
import arrow.typeclasses.binding
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val gitHubPersistWorker: GitHubPersistWorker
) : GitHubRepository {

    override fun loadRepositoriesById(profileName: String): IO<List<RepositoryData>> =
        IO.monadError().binding {
            runInAsyncContext(
                λ = {
                    githubApi.getUserRepos(profileName).execute().body() ?: emptyList()
                },
                onError = returnEmptyList,
                onSuccess = cache forwardCompose map,
                AC = IO.async()
            ).bind()
        }.fix()

    override fun getRepositoryFromCache(repositoryId: String): IO<RepositoryData?> =
        IO.monadError().binding {
            runInAsyncContext(
                λ = {
                    TODO()
                },
                onError = {
                    TODO()
                },
                onSuccess = {
                    TODO()
                },
                AC = IO.async()
            ).bind()
        }.fix()

    private fun <F, A, B> runInAsyncContext(
        λ: () -> A,
        onError: (Throwable) -> B,
        onSuccess: (A) -> B,
        AC: Async<F>
    ): Kind<F, B> =
        AC.async { proc ->
            GlobalScope.launch {
                val result = Try { λ() }.fold(onError, onSuccess)
                proc(result.right())
            }
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
        val mapped = responseList.map { repository ->
            RepositoryData(
                id = repository.id?.toInt() ?: 0,
                name = repository.name.orEmpty(),
                fullName = repository.full_name.orEmpty(),
                htmlUrl = repository.html_url.orEmpty(),
                description = repository.description.orEmpty()
            )
        }
        mapped
    }

}
