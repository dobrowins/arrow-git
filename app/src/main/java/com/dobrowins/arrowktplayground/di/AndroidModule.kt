package com.dobrowins.arrowktplayground.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import toothpick.config.Module

/**
 * @author: Artem Dobrobinsky
 */
class AndroidModule(application: Application) : Module() {

	init {
		bind(Context::class.java).toInstance(application)
		bind(Resources::class.java).toInstance(application.resources)
	}

}