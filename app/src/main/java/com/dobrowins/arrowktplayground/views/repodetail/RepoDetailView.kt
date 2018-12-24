package com.dobrowins.arrowktplayground.views.repodetail

import com.dobrowins.arrowktplayground.base.BaseView
import com.dobrowins.arrowktplayground.domain.data.RepositoryData

/**
 * @author: Artem Dobrovinsky
 */
interface RepoDetailView : BaseView {
	fun initView(repoData: RepositoryData)
	fun showSnackbar(message: String)
}