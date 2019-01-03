package com.dobrowins.arrowgit.views.repodetail

import com.dobrowins.arrowgit.base.BaseView
import com.dobrowins.arrowgit.domain.data.RepositoryData

/**
 * @author: Artem Dobrovinsky
 */
interface RepoDetailView : BaseView {
    fun initView(repoData: RepositoryData)
    fun showSnackbar(message: String)
}