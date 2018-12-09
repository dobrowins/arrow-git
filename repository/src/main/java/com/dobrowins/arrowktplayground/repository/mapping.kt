package com.dobrowins.arrowktplayground.repository

import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author Artem Dobrovinskiy
 */
val mapToRepositoryData: (RepositoryDataResponse?) -> RepositoryData? = { data ->
    if (data == null) data
    else RepositoryData(
        id = data.id,
        name = data.name,
        fullName = data.full_name,
        htmlUrl = data.html_url,
        description = data.description,
        language = data.language,
        forkedCount = data.forks,
        starredCount = data.stargazers_count
    )
}
