package com.dobrowins.arrowgit.domain

import arrow.effects.IO
import com.dobrowins.arrowgit.domain.data.GitHubRepository
import com.dobrowins.arrowgit.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ReposViewInteractor {

	override suspend fun fetchReposData(profileName: String): IO<List<RepositoryData?>> =
        gitHubRepository.loadRepositoriesById(profileName)
			.map(gitHubRepository::cache)

}
