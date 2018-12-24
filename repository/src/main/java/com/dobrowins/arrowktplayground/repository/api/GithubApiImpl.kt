package com.dobrowins.arrowktplayground.repository.api

import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import javax.inject.Inject

/**
 * @author: Artem Dobrovinsky
 */
class GithubApiImpl @Inject constructor(
	private val githubApi: GithubApi
) {

	fun getUserRepos(profileName: String): List<RepositoryDataResponse?>? =
		githubApi.getUserRepos(profileName).execute().body()

}