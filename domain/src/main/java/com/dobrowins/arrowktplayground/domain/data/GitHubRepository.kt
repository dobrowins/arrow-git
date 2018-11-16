package com.dobrowins.arrowktplayground.domain.data

import arrow.core.Either
import kotlinx.coroutines.Deferred

/**
 * @author Artem Dobrovinskiy
 */
interface GitHubRepository {
    fun loadRepositoriesById(profileName: String): Either<Throwable, List<RepositoryData?>?>
    fun getRepositoryFromCache(repositoryId: String?): Deferred<RepositoryData?>
}