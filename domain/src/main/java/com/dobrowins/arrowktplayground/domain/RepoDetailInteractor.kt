package com.dobrowins.arrowktplayground.domain

import arrow.core.Either
import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface RepoDetailInteractor {
    suspend fun getRepoData(repoId: String?): Either<Throwable, RepositoryData>
}