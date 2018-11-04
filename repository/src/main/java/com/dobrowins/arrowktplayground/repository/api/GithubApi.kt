package com.dobrowins.arrowktplayground.repository.api

import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Artem Dobrovinskiy
 */
interface GithubApi {

    @GET("/users/{userId}/repos")
    fun getUserRepos(
        @Path("userId") userId: String
    ): List<RepositoryDataResponse?>

}