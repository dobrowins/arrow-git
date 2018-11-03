package com.dobrowins.arrowktplayground.domain

import arrow.effects.IO
import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
interface ReposViewInteractor {
    fun fetchReposData(userId: String): IO<List<RepositoryData>>
}