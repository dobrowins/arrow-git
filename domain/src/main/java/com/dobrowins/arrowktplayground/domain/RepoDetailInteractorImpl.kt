package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailInteractorImpl @Inject constructor(
	private val gitHubRepository: GitHubRepository
) : RepoDetailInteractor {

	override suspend fun getRepoData(repoId: String?): IO<RepositoryData?> =
		gitHubRepository.getRepositoryFromCache(repoId)

}
