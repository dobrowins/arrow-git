package com.dobrowins.arrowktplayground.domain

import arrow.core.Either
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailInteractorImpl @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : RepoDetailInteractor {

    override fun getRepoData(repoId: String?): Either<Throwable, RepositoryData> =
        TODO()

}