package com.dobrowins.arrowktplayground.repository.cache

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions
import arrow.effects.fix
import arrow.typeclasses.binding
import com.dobrowins.arrowktplayground.repository.RepositoryDataResponse
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorker @Inject constructor() : PersistWorker() {

    private val bookName = "github response cache"
    private val keyReposCache = "github response repos cache"

    fun put(repos: List<RepositoryDataResponse>) {
        ForIO extensions {
            binding {
                put(keyReposCache, repos, bookName)
            }
        }
    }

    fun getRepository(repositoryId: String?): IO<RepositoryDataResponse> =
        ForIO extensions {
            binding {
                TODO("")
            }.fix()
        }

}