package com.dobrowins.arrowgit.views.start

import com.dobrowins.arrowgit.base.BaseView

/**
 * @author: Artem Dobrovinsky
 */
interface StartView : BaseView {
	fun startReposFragment(profileName: String)
	fun showSnackbar(message: String)
}