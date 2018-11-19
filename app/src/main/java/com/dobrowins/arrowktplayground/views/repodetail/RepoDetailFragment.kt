package com.dobrowins.arrowktplayground.views.repodetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.views.KEY_REPO_NAME
import kotlinx.android.synthetic.main.fragment_repo_detail.rootFragmentRepoDetail
import kotlinx.android.synthetic.main.fragment_repo_detail.tbFragmentRepoDetail
import kotlinx.android.synthetic.main.fragment_repo_detail.tvFragmentRepoDetailDescription
import kotlinx.android.synthetic.main.fragment_repo_detail.tvFragmentRepoDetailTitle
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
interface RepoDetailView : BaseView {
	fun initView(repoData: RepositoryData)
	fun showSnackbar(message: String)
}

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

	override fun showSnackbar(message: String) {
		super.showSnackbar(rootFragmentRepoDetail)(message)
	}

	private fun initToolbar() =
		tbFragmentRepoDetail.run {
			navigationIcon =
					ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white_24dp)
			setNavigationOnClickListener { presenter.onNavigationIconClicked() }
		}

}