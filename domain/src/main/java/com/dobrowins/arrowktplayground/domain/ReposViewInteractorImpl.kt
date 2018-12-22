package com.dobrowins.arrowktplayground.domain

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.syntax.function.forwardCompose
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ReposViewInteractor {

    override suspend fun fetchReposData(profileName: String): Either<Throwable, List<RepositoryData>> =
        gitHubRepository.loadRepositoriesById(profileName)
            .fold(
                ifFailure = ::Left,
                ifSuccess = gitHubRepository::cache forwardCompose ::Right
            )

}
