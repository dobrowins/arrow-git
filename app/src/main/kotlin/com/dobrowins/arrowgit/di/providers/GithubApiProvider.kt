package com.dobrowins.arrowgit.di.providers

import com.dobrowins.arrowgit.repository.api.GithubApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Artem Dobrovinskiy
 */
class GithubApiProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<GithubApi> {

    override fun get(): GithubApi = retrofit.create(GithubApi::class.java)

}
