package com.dobrowins.arrowgit.views.repodetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.base.BaseFragment
import com.dobrowins.arrowgit.domain.data.RepositoryData
import com.dobrowins.arrowgit.views.KEY_REPO_NAME
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailFragment : BaseFragment(), RepoDetailView {

    companion object {
        fun getInstance(repoId: String): RepoDetailFragment {
            val fragment = RepoDetailFragment()
            val bundle = Bundle()
            bundle.putString(KEY_REPO_NAME, repoId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutId: Int = R.layout.fragment_repo_detail

    @Inject
    @InjectPresenter
    lateinit var presenter: RepoDetailPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var repoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoName = arguments?.getString(KEY_REPO_NAME).orEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        presenter.attachView(this)
        presenter.fetchRepoData(repoName)
    }

    override fun initView(repoData: RepositoryData) = runOnUiThread {
        val name = repoData.fullName
        val description = repoData.description
        name.let(tvFragmentRepoDetailTitle::setText)
        description.let(tvFragmentRepoDetailDescription::setText)
    }

    override fun showSnackbar(message: String) =
        super.showSnackbar(rootFragmentRepoDetail, message)

    private fun initToolbar() =
        tbFragmentRepoDetail.run {
            navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { presenter.onNavigationIconClicked() }
        }

    override fun onDestroyView() {
        presenter.detachView(this)
        super.onDestroyView()
    }

}