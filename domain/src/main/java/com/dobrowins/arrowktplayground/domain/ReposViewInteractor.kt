package com.dobrowins.arrowktplayground.domain

import arrow.core.Either
import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface ReposViewInteractor {
    suspend fun fetchReposData(profileName: String): Either<Throwable, List<RepositoryData?>>
}