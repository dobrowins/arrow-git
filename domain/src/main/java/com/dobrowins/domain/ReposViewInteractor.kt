package com.dobrowins.domain

import arrow.effects.IO
import com.dobrowins.domain.data.GitHubRepository
import com.dobrowins.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
): ReposViewInteractor {

    override fun fetchReposData(userId: String): IO<List<RepositoryData>> =
        gitHubRepository.loadRepositoriesById(userId)

}

interface ReposViewInteractor {
    fun fetchReposData(userId: String): IO<List<RepositoryData>>
}