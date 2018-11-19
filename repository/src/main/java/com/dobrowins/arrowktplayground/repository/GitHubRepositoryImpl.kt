package com.dobrowins.arrowktplayground.repository

import arrow.Kind
import arrow.core.Try
import arrow.core.fix
import arrow.core.right
import arrow.effects.IO
import arrow.effects.typeclasses.Async
import arrow.instances.`try`.monadError.monadError
import arrow.typeclasses.bindingCatch
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl @Inject constructor(
	private val githubApi: GithubApi,
	private val gitHubPersistWorker: GitHubPersistWorker
) : GitHubRepository {

	override fun loadRepositoriesById(profileName: String): IO<List<RepositoryData>> =
		Try.monadError().bindingCatch {
			getUserReposUnsafe(profileName)
				?.filterNotNull()
				?.let(cache)
				?.map(mapToRepositoryData)
				?: emptyList()
		}
			.fix()
			.fold(
				ifFailure = { t -> IO.raiseError(t) },
				ifSuccess = { list -> IO.just(list) }
			)

	override fun getRepositoryFromCache(repositoryId: String?): IO<RepositoryData> =
		IO { gitHubPersistWorker.getRepositoryFromCache(repositoryId) }
			.map(mapToRepositoryData)

	private val getUserReposUnsafe: (String) -> List<RepositoryDataResponse?>? =
		{ s -> githubApi.getUserRepos(s).execute().body() }

	private val cache: (List<RepositoryDataResponse?>?) -> List<RepositoryDataResponse> =
		{ responseList ->
			responseList?.let { list ->
				val filtered = list.filterNotNull()
				gitHubPersistWorker.put(filtered)
				filtered
			} ?: emptyList()
		}

	private val mapToRepositoryData: (RepositoryDataResponse?) -> RepositoryData = { data ->
		RepositoryData(
			id = data?.id?.toInt() ?: 0,
			name = data?.name.orEmpty(),
			fullName = data?.full_name.orEmpty(),
			htmlUrl = data?.html_url.orEmpty(),
			description = data?.description.orEmpty()
		)
	}

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
}

