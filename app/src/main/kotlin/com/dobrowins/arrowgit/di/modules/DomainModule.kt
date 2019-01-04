package com.dobrowins.arrowgit.di.modules

import com.dobrowins.arrowgit.domain.*
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