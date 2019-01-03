package com.dobrowins.arrowgit.repository.api

import com.dobrowins.arrowgit.repository.RepositoryDataResponse
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