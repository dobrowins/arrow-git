package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface RepoDetailInteractor {
    suspend fun getRepoData(repoId: String?): IO<RepositoryData?>
}