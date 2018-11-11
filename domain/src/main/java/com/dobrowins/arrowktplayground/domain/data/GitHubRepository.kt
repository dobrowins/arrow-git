package com.dobrowins.arrowktplayground.domain.data

import arrow.effects.IO

/**
 * @author Artem Dobrovinskiy
 */
interface GitHubRepository {
    fun loadRepositoriesById(profileName: String): IO<List<RepositoryData>>
    fun getRepositoryFromCache(repositoryId: String): IO<RepositoryData?>
}