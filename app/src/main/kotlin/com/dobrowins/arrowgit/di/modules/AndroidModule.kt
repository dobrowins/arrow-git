package com.dobrowins.arrowgit.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.dobrowins.arrowgit.di.providers.DispatchersProviderImpl
import com.dobrowins.arrowgit.domain.DispatchersProvider
import toothpick.config.Module

/**
 * @author: Artem Dobrovinsky
 */
class AndroidModule(application: Application) : Module() {

    init {
        bind(Context::class.java).toInstance(application)
        bind(Resources::class.java).toInstance(application.resources)
        bind(DispatchersProvider::class.java).toInstance(DispatchersProviderImpl())
    }

}