package com.dobrowins.arrowktplayground.repository

import arrow.Kind
import arrow.core.Try
import arrow.core.right
import arrow.effects.IO
import arrow.effects.typeclasses.Async
import com.dobrowins.arrowktplayground.domain.DispatchersProvider
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
	private val gitHubPersistWorker: GitHubPersistWorker,
	dispatchersProvider: DispatchersProvider
) : GitHubRepository {

	private val ioScope = dispatchersProvider.io

	override fun loadRepositoriesById(profileName: String): IO<List<RepositoryData?>?> =
		IO.invoke(ioScope) {
			getUserReposUnsafe(profileName)
				?.filterNotNull()
				?.let(cache)
				?.map(mapToRepositoryData)
		}

	override fun getRepositoryFromCache(repositoryId: String?): IO<RepositoryData?> =
		IO.invoke(ioScope) { gitHubPersistWorker.getRepositoryFromCache(repositoryId) }
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

