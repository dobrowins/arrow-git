package com.dobrowins.arrowktplayground.views

import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView

/**
 * @author: Artem Dobrobinsky
 */
class ReposFragment : BaseFragment(), ReposView {

	override val layoutId: Int = R.layout.fragment_repos

}

interface ReposView : BaseView {

}