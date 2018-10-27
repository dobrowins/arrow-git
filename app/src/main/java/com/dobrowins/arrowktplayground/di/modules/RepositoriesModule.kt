package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.repository.GitHubRepositoryImpl
import com.dobrowins.domain.data.GitHubRepository
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class RepositoriesModule : Module() {
    init {
        bind(GitHubRepository::class.java) to GitHubRepositoryImpl::class.java
    }
}