package com.dobrowins.arrowgit.views.repos

import com.dobrowins.arrowgit.base.BaseView
import com.dobrowins.arrowgit.base.adapters.HolderType

/**
 * @author: Artem Dobrovinsky
 */
interface ReposView : BaseView {
    fun showItems(repos: List<HolderType>)
    fun showSnackbar(message: String)
}