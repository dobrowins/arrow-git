package com.dobrowins.arrowktplayground.views.repos

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView
import com.dobrowins.arrowktplayground.views.KEY_REPO_ID
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

    private lateinit var repoId: String

    override val layoutId: Int = R.layout.fragment_repos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoId = arguments?.getString(KEY_REPO_ID).orEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbFragmentRepos.apply {
            title = null
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { presenter.onToolbarNavigationIconPressed() }
        }
        presenter.loadData(repoId)
    }

    override fun showRepos(repos: List<RepoItem>) {
        val reposAdapter = ReposAdapter(
            repoOnClickFunc = {
                // TODO: open repo detailed info
            }
        )
        reposAdapter.add(repos)
        rvReposFragment.run {
            adapter = reposAdapter
            setHasFixedSize(true)
        }
        TransitionManager.beginDelayedTransition(rootFragmentRepos)
        rvReposFragment.visibility = View.VISIBLE
    }

    override fun showToast(text: String) =
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

}

interface ReposView : BaseView {
    fun showRepos(repos: List<RepoItem>)
    fun showToast(text: String)
}