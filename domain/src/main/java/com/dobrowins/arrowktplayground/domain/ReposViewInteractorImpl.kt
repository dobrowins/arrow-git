package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ReposViewInteractor {

    override fun fetchReposData(profileName: String): IO<List<RepositoryData>> =
        gitHubRepository.loadRepositoriesById(profileName)

}
