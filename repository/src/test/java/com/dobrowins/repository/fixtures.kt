package com.dobrowins.repository

import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse

/**
 * @author Artem Dobrovinskiy
 */
object GithubApiFixture {

	val repositoryData: RepositoryData?
		get() = RepositoryData(
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		)

	val repositoryDataResponseList: List<RepositoryDataResponse?>?
		get() = listOf(
			repositoryDataResponse
		)

	val repositoryDataResponse: RepositoryDataResponse
		get() = RepositoryDataResponse()
}