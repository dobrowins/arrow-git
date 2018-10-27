package com.dobrowins.domain

import com.dobrowins.domain.data.GitHubRepository
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewInteractorImpl @Inject constructor (
    private val gitHubRepository: GitHubRepository
) {



}

interface ReposViewInteractor {

}