package com.dobrowins.arrowktplayground.views.repos

import com.dobrowins.arrowktplayground.base.BaseAdapter
import com.dobrowins.arrowktplayground.base.adapters.HolderType

/**
 * @author Artem Dobrovinskiy
 */
class ReposAdapter(
    repoOnClickFunc: (String) -> Unit
) : BaseAdapter() {

    init {
        delegates.add(HolderType.REPO, RepoDelegate(repoOnClickFunc))
        // TODO: add fullscreen error VH
    }

}
