package com.dobrowins.arrowktplayground.views.repodetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.views.KEY_REPO_ID
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
interface RepoDetailView : BaseView {
	fun initView(repoData: RepositoryData)
	fun displayError(t: Throwable)
}

class RepoDetailFragment : BaseFragment(), RepoDetailView {

	companion object {
		fun getInstance(repoId: String): RepoDetailFragment {
			val fragment = RepoDetailFragment()
			val bundle = Bundle()
			bundle.putString(KEY_REPO_ID, repoId)
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

	private lateinit var repoId: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		repoId = arguments?.getString(KEY_REPO_ID).orEmpty()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initToolbar()
		presenter.fetchRepoData(repoId)
	}

	override fun initView(repoData: RepositoryData) = runOnUiThread {
		val name = repoData.fullName
		val description = repoData.description
		name.let(tvFragmentRepoDetailTitle::setText)
		description.let(tvFragmentRepoDetailDescription::setText)
	}

	override fun displayError(t: Throwable) = runOnUiThread {
		mapThrowableMessage forwardCompose showSnackbar(rootFragmentRepoDetail)
	}

	private fun initToolbar() =
		tbFragmentRepoDetail.run {
			navigationIcon =
					ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white_24dp)
			setNavigationOnClickListener { presenter.onNavigationIconClicked() }
		}

}