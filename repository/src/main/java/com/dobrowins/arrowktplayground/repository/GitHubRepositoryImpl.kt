package com.dobrowins.arrowktplayground.repository

import arrow.Kind
import arrow.core.Either
import arrow.core.Try
import arrow.core.andThen
import arrow.core.fix
import arrow.core.right
import arrow.effects.IO
import arrow.effects.fix
import arrow.effects.typeclasses.Async
import arrow.instances.`try`.monadError.monadError
import arrow.syntax.function.forwardCompose
import arrow.typeclasses.MonadError
import arrow.typeclasses.bindingCatch
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import kotlinx.coroutines.Deferred
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

    override fun loadRepositoriesById(profileName: String): Either<Throwable, List<RepositoryData>> =
        Try.just(githubApi.getUserRepos(profileName).execute().body()).fold(
            ifFailure = { Either.Left(it) },
            ifSuccess = cache andThen mapToEither
        )

    override fun getRepositoryFromCache(repositoryId: String?): Deferred<RepositoryData?> =
       TODO()

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

    private val cache: (List<RepositoryDataResponse?>?) -> List<RepositoryDataResponse> =
        { responseList ->
            responseList?.let { list ->
                val filtered = list.filterNotNull()
                gitHubPersistWorker.put(filtered)
                filtered
            } ?: emptyList()
        }

    private val mapToEither: (List<RepositoryDataResponse>) -> Either<Nothing, List<RepositoryData>> = { responseList ->
        val mapped = responseList.map { repository ->
            RepositoryData(
                id = repository.id?.toInt() ?: 0,
                name = repository.name.orEmpty(),
                fullName = repository.full_name.orEmpty(),
                htmlUrl = repository.html_url.orEmpty(),
                description = repository.description.orEmpty()
            )
        }
        Either.Right(mapped)
    }

}
