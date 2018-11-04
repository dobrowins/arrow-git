package com.dobrowins.arrowktplayground.views.repos

import com.dobrowins.arrowktplayground.base.adapters.HolderType

/**
 * @author Artem Dobrovinskiy
 */

data class RepoItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val description: String
) : HolderType {
    override fun type(): Int = HolderType.REPO
}