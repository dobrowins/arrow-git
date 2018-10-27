package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.domain.ReposViewInteractor
import com.dobrowins.domain.ReposViewInteractorImpl
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class DomainModule : Module() {

    init {
        bind(ReposViewInteractor::class.java).to(ReposViewInteractorImpl::class.java)
    }

}