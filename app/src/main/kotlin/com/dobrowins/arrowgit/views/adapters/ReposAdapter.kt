package com.dobrowins.arrowgit.views.adapters

import com.dobrowins.arrowgit.base.BaseAdapter
import com.dobrowins.arrowgit.base.adapters.HolderType
import com.dobrowins.arrowgit.views.delegates.ErrorDelegate
import com.dobrowins.arrowgit.views.delegates.RepoDelegate

/**
 * @author Artem Dobrovinskiy
 */
class ReposAdapter(
    repoOnClickFunc: (String) -> Unit,
    errorOnClickFunc: () -> Unit
) : BaseAdapter() {

    init {
        delegates.add(HolderType.REPO, RepoDelegate(repoOnClickFunc))
        delegates.add(HolderType.ERROR, ErrorDelegate(errorOnClickFunc))
    }

}
