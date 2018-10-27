package com.dobrowins.arrowktplayground.views

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class ReposViewPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<ReposView>() {

    fun loadData(repoId: String) {

    }

    fun onToolbarNavigationIconPressed(): (View) -> Unit = {
        router.exit()
    }


}