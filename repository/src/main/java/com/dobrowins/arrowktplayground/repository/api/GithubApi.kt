package com.dobrowins.arrowktplayground.repository.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * @author Artem Dobrovinskiy
 */
interface GithubApi {

    @GET("/users/{userId}/repos")
    fun getUserRepos(
        @Path("userId") userId: String
    )

    @GET("/users/{userId}/")
    fun getUserData(
        @Path("userId") userId: String
    )

}