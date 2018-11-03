package com.dobrowins.arrowktplayground.domain.data

import arrow.effects.IO

/**
 * @author Artem Dobrovinskiy
 */
interface GitHubRepository {
    fun loadRepositoriesById(userId: String): IO<List<RepositoryData>>
}