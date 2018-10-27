package com.dobrowins.arrowktplayground.di.modules

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * @author: Artem Dobrovinsky
 */
class NavigationModule : Module() {

	private val cicerone: Cicerone<Router> = Cicerone.create()

	init {
		bind(Router::class.java).toInstance(cicerone.router)
		bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
	}

}