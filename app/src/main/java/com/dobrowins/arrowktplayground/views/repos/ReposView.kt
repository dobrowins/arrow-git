package com.dobrowins.arrowktplayground.views.repos

import com.dobrowins.arrowktplayground.base.BaseView

/**
 * @author: Artem Dobrovinsky
 */
interface ReposView : BaseView {
	fun showRepos(repos: List<RepoItem>)
	fun showSnackbar(message: String)
	fun showErrorItem()
}