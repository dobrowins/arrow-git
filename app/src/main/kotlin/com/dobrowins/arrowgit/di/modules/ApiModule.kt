package com.dobrowins.arrowgit.di.modules

import com.dobrowins.arrowgit.BuildConfig
import com.dobrowins.arrowgit.di.BaseUrlPath
import com.dobrowins.arrowgit.di.providers.GithubApiProvider
import com.dobrowins.arrowgit.di.providers.GsonProvider
import com.dobrowins.arrowgit.di.providers.OkHttpClientProvider
import com.dobrowins.arrowgit.di.providers.RetrofitProvider
import com.dobrowins.arrowgit.repository.api.GithubApi
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
