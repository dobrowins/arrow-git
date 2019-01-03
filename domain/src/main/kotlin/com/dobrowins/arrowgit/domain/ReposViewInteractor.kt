package com.dobrowins.arrowgit.domain

import arrow.effects.IO
import com.dobrowins.arrowgit.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface ReposViewInteractor {
    suspend fun fetchReposData(profileName: String): IO<List<RepositoryData?>>
}