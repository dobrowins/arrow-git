package com.dobrowins.arrowktplayground.di.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class GsonProvider : Provider<Gson> {

    override fun get() = GsonBuilder().create()

}