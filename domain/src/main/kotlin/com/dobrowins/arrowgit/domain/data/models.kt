package com.dobrowins.arrowgit.domain.data

/**
 * @author Artem Dobrovinskiy
 */
data class RepositoryData(
    val id: String?,
    val name: String?,
    val fullName: String?,
    val htmlUrl: String?,
    val description: String?,
    val language: String?,
    val forkedCount: Int?,
    val starredCount: Int?
)
