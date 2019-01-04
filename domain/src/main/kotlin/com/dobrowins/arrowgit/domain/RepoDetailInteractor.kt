package com.dobrowins.arrowgit.domain

import arrow.effects.IO
import com.dobrowins.arrowgit.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface RepoDetailInteractor {
    suspend fun getRepoData(repoId: String?): IO<RepositoryData?>
}