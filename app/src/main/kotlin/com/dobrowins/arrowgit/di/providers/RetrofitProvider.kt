package com.dobrowins.arrowgit.di.providers

import com.dobrowins.arrowgit.di.BaseUrlPath
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class RetrofitProvider @Inject constructor(
    private val gson: Gson,
    private val okHttpClient: OkHttpClient,
    @BaseUrlPath private val baseUrl: String
) : Provider<Retrofit> {

    override fun get(): Retrofit = Retrofit.Builder()
        .apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
            baseUrl(baseUrl)
        }
        .build()
}