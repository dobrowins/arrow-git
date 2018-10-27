package com.dobrowins.arrowktplayground.di.providers

import com.dobrowins.arrowktplayground.repository.api.GithubApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class GithubApiProvider @Inject constructor(
    private val retrofit: Retrofit
): Provider<GithubApi> {

    override fun get(): GithubApi = retrofit.create(GithubApi::class.java)

}
