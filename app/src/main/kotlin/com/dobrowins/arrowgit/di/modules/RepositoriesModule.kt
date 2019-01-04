package com.dobrowins.arrowgit.di.modules

import com.dobrowins.arrowgit.di.providers.GitHubRepositoryImplProvider
import com.dobrowins.arrowgit.domain.data.GitHubRepository
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class RepositoriesModule : Module() {
    init {
        bind(GitHubRepository::class.java).toProvider(GitHubRepositoryImplProvider::class.java)
            .singletonInScope()
    }
}

