package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.domain.*
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class DomainModule : Module() {

    init {
        bind(StartViewInteractor::class.java).to(StartViewInteractorImpl::class.java)
        bind(ReposViewInteractor::class.java).to(ReposViewInteractorImpl::class.java)
        bind(RepoDetailInteractor::class.java).to(RepoDetailInteractorImpl::class.java)
    }

}