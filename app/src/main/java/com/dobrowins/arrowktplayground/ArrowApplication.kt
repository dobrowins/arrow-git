package com.dobrowins.arrowktplayground

import android.app.Application
import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author: Artem Dobrobinsky
 */
class ArrowApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		isDebug.fold(setDebugOptions, setReleaseOptions)
	}

}

val isDebug: Option<Boolean> = if (BuildConfig.DEBUG) Some(true) else None

val setDebugOptions: () -> Unit = {
	Timber.plant(DebugTree())
}

val setReleaseOptions: (Boolean) -> Unit = {

}

val cicerone: Cicerone<Router> = Cicerone.create()
val navigatorHolder: NavigatorHolder = cicerone.navigatorHolder
val navigationRouter: Router = cicerone.router