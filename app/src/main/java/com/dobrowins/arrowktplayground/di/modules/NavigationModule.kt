package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.di.providers.CiceroneProvider
import com.dobrowins.arrowktplayground.di.providers.NavigatorHolderProvider
import com.dobrowins.arrowktplayground.di.providers.RouterProvider
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * @author: Artem Dobrovinsky
 */
class NavigationModule : Module() {

//	private val cicerone: Cicerone<Router> = Cicerone.create()
//
//	init {
//		bind(Router::class.java).toInstance(cicerone.router)
//		bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
//	}

    init {
        bind(Cicerone::class.java).toProvider(CiceroneProvider::class.java).singletonInScope()
        bind(Router::class.java).toProvider(RouterProvider::class.java).singletonInScope()
        bind(NavigatorHolder::class.java).toProvider(NavigatorHolderProvider::class.java)
            .singletonInScope()
    }

}