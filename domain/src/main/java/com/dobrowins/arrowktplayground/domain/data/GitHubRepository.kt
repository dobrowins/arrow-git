package com.dobrowins.arrowktplayground.domain.data

import arrow.core.Try
import arrow.effects.IO

/**
 * @author Artem Dobrovinskiy
 */
interface GitHubRepository {
    fun loadRepositoriesById(profileName: String): Try<List<RepositoryData?>?>
    fun cache(responseList: List<RepositoryData?>?): List<RepositoryData>
    fun getRepositoryFromCache(repositoryId: String?): IO<RepositoryData?>
}