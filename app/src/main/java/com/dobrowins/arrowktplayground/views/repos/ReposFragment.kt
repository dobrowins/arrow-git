package com.dobrowins.arrowktplayground.views.repos

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager
import android.view.View
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

    companion object {
        fun getInstance(data: String): ReposFragment {
            val fragment = ReposFragment()
            val bundle = Bundle()
            bundle.putString(KEY_REPO_ID, data)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: ReposViewPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override val layoutId: Int = R.layout.fragment_repos

    private lateinit var repoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoId = arguments?.getString(KEY_REPO_ID).orEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        loadRepos()
    }

    private fun initToolbar() {
        tbFragmentRepos.apply {
            title = null
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { presenter.onToolbarNavigationIconPressed() }
        }
    }

    private fun loadRepos() {
        presenter.loadData(repoId).unsafeRunAsync { either ->
            either.fold(
                { showRepos(emptyList()) },
                { items -> showRepos(items) }
            )
        }
    }

    override fun showRepos(repos: List<RepoItem>): Unit = runOnUiThread {
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

    override fun showSnackbar(text: String) = runOnUiThread {
        Snackbar.make(rootFragmentRepos, text, Snackbar.LENGTH_SHORT).show()
    }

}

interface ReposView : BaseView {
    fun showRepos(repos: List<RepoItem>)
    fun showSnackbar(text: String)
}