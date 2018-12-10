package com.dobrowins.repository

import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse

/**
 * @author Artem Dobrovinskiy
 */
object GithubApiFixture {

    val repositoryDataResponseList: List<RepositoryDataResponse?>?
        get() = listOf(
            repositoryDataResponse
        )

    val repositoryDataResponse: RepositoryDataResponse
        get() = RepositoryDataResponse()
}