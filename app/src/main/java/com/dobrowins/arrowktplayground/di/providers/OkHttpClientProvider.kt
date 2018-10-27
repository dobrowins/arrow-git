package com.dobrowins.arrowktplayground.di.providers

import com.dobrowins.arrowktplayground.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class OkHttpClientProvider : Provider<OkHttpClient> {

    companion object {
        private const val TIMEOUT_SECONDS = 30L
    }

    override fun get() = OkHttpClient.Builder().apply {
        connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG)
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }
        .addInterceptor(HeadersInterceptor())
        .build()

}

// intercept to apply github accept header
class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
            .build()
        return chain.proceed(newRequest)
    }
}