package com.dobrowins.arrowktplayground.di.providers

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class CiceroneProvider @Inject constructor() : Provider<Cicerone<Router>> {
    override fun get(): Cicerone<Router> = Cicerone.create()
}

class RouterProvider @Inject constructor(
    private val cicerone: Cicerone<Router>
) : Provider<Router> {
    override fun get(): Router = cicerone.router
}

class NavigatorHolderProvider
@Inject constructor(
    private val cicerone: Cicerone<Router>
) : Provider<NavigatorHolder> {
    override fun get(): NavigatorHolder = cicerone.navigatorHolder
}