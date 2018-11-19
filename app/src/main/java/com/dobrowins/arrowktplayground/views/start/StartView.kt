package com.dobrowins.arrowktplayground.views.start

import com.dobrowins.arrowktplayground.base.BaseView

/**
 * @author: Artem Dobrovinsky
 */
interface StartView : BaseView {
	fun startReposFragment(profileName: String)
	fun showSnackbar(message: String)
}