package com.dobrowins.arrowgit.di.providers

import com.dobrowins.arrowgit.domain.DispatchersProvider
import com.dobrowins.arrowgit.repository.GitHubRepositoryImpl
import com.dobrowins.arrowgit.repository.api.GithubApiImpl
import com.dobrowins.arrowgit.repository.cache.GitHubPersistWorker
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