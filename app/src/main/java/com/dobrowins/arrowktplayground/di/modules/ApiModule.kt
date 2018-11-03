package com.dobrowins.arrowktplayground.di.modules

import com.dobrowins.arrowktplayground.BuildConfig
import com.dobrowins.arrowktplayground.di.BaseUrlPath
import com.dobrowins.arrowktplayground.di.providers.GithubApiProvider
import com.dobrowins.arrowktplayground.di.providers.GsonProvider
import com.dobrowins.arrowktplayground.di.providers.OkHttpClientProvider
import com.dobrowins.arrowktplayground.di.providers.RetrofitProvider
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

/**
 * @author Artem Dobrovinskiy
 */
class ApiModule : Module() {
    init {
        bind(String::class.java).withName(BaseUrlPath::class.java).toInstance(BuildConfig.BASE_URL)
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java).providesSingletonInScope()
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingletonInScope()
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(GithubApi::class.java).toProvider(GithubApiProvider::class.java).providesSingletonInScope()
    }
}
