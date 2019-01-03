package com.dobrowins.arrowgit.domain.data

import arrow.effects.IO

/**
 * @author Artem Dobrovinskiy
 */
interface GitHubRepository {
    suspend fun loadRepositoriesById(profileName: String): IO<List<RepositoryData?>>
    fun cache(responseList: List<RepositoryData?>?): List<RepositoryData>
    suspend fun getRepositoryFromCache(repositoryId: String?): IO<RepositoryData?>
}