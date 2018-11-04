package com.dobrowins.arrowktplayground.di.providers

import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.arrowktplayground.repository.cache.GitHubPersistWorker
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImplProvider
@Inject constructor(
    private val githubApi: GithubApi,
    private val gitHubPersistWorker: GitHubPersistWorker
) : Provider<GitHubRepositoryImpl> {

    override fun get(): GitHubRepositoryImpl =
        GitHubRepositoryImpl(githubApi, gitHubPersistWorker)

}