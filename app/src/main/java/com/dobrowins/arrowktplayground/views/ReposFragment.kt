package com.dobrowins.arrowktplayground.views

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView
import kotlinx.android.synthetic.main.fragment_repos.*
import javax.inject.Inject

/**
 * @author: Artem Dobrovinsky
 */
class ReposFragment : BaseFragment(), ReposView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ReposViewPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

	override val layoutId: Int = R.layout.fragment_repos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repoId = arguments?.getString(KEY_REPO_ID)
        repoId?.let(presenter::loadData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbFragmentRepos.apply {
            title = null
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { presenter::onToolbarNavigationIconPressed::invoke }
        }
    }


}

interface ReposView : BaseView {

}