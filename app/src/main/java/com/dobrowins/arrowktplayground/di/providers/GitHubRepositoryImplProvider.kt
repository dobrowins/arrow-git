package com.dobrowins.arrowktplayground.di.providers

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.api.GithubApiImpl
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImplProvider
@Inject constructor(
    private val githubApiImpl: GithubApiImpl,
	private val gitHubPersistWorker: GitHubPersistWorker,
	private val dispatchersProvider: DispatchersProvider
) : Provider<GitHubRepositoryImpl> {

    override fun get(): GitHubRepositoryImpl =
		GitHubRepositoryImpl(githubApiImpl, gitHubPersistWorker, dispatchersProvider)

}