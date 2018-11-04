package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.di.providers.GitHubRepositoryImplProvider
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
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

