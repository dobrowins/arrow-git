package com.dobrowins.arrowktplayground.views.repos

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.views.KEY_PROFILE_NAME
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
			bundle.putString(KEY_PROFILE_NAME, data)
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

	private lateinit var profileName: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		profileName = arguments?.getString(KEY_PROFILE_NAME).orEmpty()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.attachView(this)
		initToolbar()
		loadRepos()
	}

	override fun showSnackbar(message: String) = showSnackbar(rootFragmentRepos, message)

	override fun showErrorItem() = TODO("show fullscreen error item")

	private fun initToolbar() {
		tbFragmentRepos.apply {
			title = profileName
			setTitleTextColor(
				ContextCompat.getColor(
					activity?.baseContext as Context,
					android.R.color.white
				)
			)
			setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
			setNavigationOnClickListener { presenter.onToolbarNavigationIconPressed() }
		}
	}

	private fun loadRepos() = presenter.loadData(profileName)

	override fun showRepos(repos: List<RepoItem>): Unit = runOnUiThread {
		val reposAdapter = ReposAdapter(
			repoOnClickFunc = presenter::onRepoItemClicked
		)
		reposAdapter.add(repos)
		rvReposFragment.run {
			adapter = reposAdapter
			setHasFixedSize(true)
			AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_animation_fall_down)
				?.let(::setLayoutAnimation)
		}
	}

}
