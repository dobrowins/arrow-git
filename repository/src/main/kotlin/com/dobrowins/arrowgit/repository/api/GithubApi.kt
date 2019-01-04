package com.dobrowins.arrowgit.repository.api

import com.dobrowins.arrowgit.repository.RepositoryDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Artem Dobrovinskiy
 */
interface GithubApi {

    @GET("/users/{userId}/repos")
    fun getUserRepos(
        @Path("userId") userId: String
    ): Call<List<RepositoryDataResponse?>?>

}