package com.dobrowins.arrowgit.views.repos

import com.dobrowins.arrowgit.base.adapters.HolderType

/**
 * @author Artem Dobrovinskiy
 */

data class RepoItem(
    val id: String,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val description: String,
    val language: String,
    val forkedText: String,
    val starredText: String
) : HolderType {
    override fun type(): Int = HolderType.REPO
}

data class ErrorItem(
    val message: String?
) : HolderType {
    override fun type(): Int = HolderType.ERROR
}