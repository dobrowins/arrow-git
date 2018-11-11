package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.domain.RepoDetailInteractor
import com.dobrowins.arrowktplayground.domain.RepoDetailInteractorImpl
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.ReposViewInteractorImpl
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class DomainModule : Module() {

    init {
        bind(ReposViewInteractor::class.java).to(ReposViewInteractorImpl::class.java)
        bind(RepoDetailInteractor::class.java).to(RepoDetailInteractorImpl::class.java)
    }

}