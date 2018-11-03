package com.dobrowins.arrowktplayground

import android.app.Application
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import com.dobrowins.arrowktplayground.di.*
import com.dobrowins.arrowktplayground.di.modules.*
import io.paperdb.Paper
import timber.log.Timber
import timber.log.Timber.DebugTree
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

/**
 * @author: Artem Dobrovinsky
 */
class ArrowApplication : Application() {

	private val isDebugOption: Option<Boolean> = if (BuildConfig.DEBUG) Some(true) else None

	private val setDebugOptions: () -> Unit = {
		Timber.plant(DebugTree())
	}

	private val setReleaseOptions: (Boolean) -> Unit = {

	}

	private val setDebugToothpick: (Boolean) -> Unit = {
		Toothpick.setConfiguration(Configuration.forDevelopment().enableReflection())
	}

	private val setReleaseToothpick: () -> Unit = {
		Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
		FactoryRegistryLocator.setRootRegistry(com.dobrowins.arrowktplayground.FactoryRegistry())
		MemberInjectorRegistryLocator.setRootRegistry(com.dobrowins.arrowktplayground.MemberInjectorRegistry())
	}

	override fun onCreate() {
		super.onCreate()
		isDebugOption.fold(setDebugOptions, setReleaseOptions)
		initToothpick()
        initPaper()
	}

    private fun initPaper() = Paper.init(this@ArrowApplication)

	private fun initToothpick() =
		isDebugOption.fold(setReleaseToothpick, setDebugToothpick)
			.also {
				val appScope = Toothpick.openScope(Scopes.APPLICATION)
				appScope.installModules(
                    AndroidModule(this@ArrowApplication),
                    NavigationModule(),
                    DomainModule(),
                    RepositoriesModule(),
                    ApiModule()
				)
				Toothpick.inject(this@ArrowApplication, appScope)
			}

}
