package com.dobrowins.domain

import arrow.effects.fix
import com.dobrowins.domain.data.GitHubRepository
import com.dobrowins.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
): ReposViewInteractor {

    override fun fetchReposData(userId: String): List<RepositoryData> =
        gitHubRepository.loadRepositoriesById(userId)
            .unsafeRunSync()

}

interface ReposViewInteractor {
    fun fetchReposData(userId: String): List<RepositoryData>
}