package com.dobrowins.arrowktplayground.repository

import arrow.data.Ior
import arrow.effects.IO
import com.dobrowins.arrowktplayground.repository.api.GithubApi
import com.dobrowins.domain.data.GitHubRepository
import com.dobrowins.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
class GitHubRepositoryImpl(
    private val githubApi: GithubApi
) : GitHubRepository {

    fun loadRepositoriesById(userId: String): IO<List<RepositoryData>> = IO {



        TODO ("implement me later, please")
    }

}