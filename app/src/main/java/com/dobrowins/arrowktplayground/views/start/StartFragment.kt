package com.dobrowins.arrowktplayground.views.start

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_start.etStartFragment
import kotlinx.android.synthetic.main.fragment_start.rootStartFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class StartFragment : BaseFragment(), StartView {

	@Inject
	@InjectPresenter
	lateinit var presenter: StartViewPresenter

	@ProvidePresenter
	fun providePresenter() = presenter

	override val layoutId: Int = R.layout.fragment_start

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.attachView(this@StartFragment)
		etStartFragment.run {
			setOnFocusChangeListener { _, hasFocus ->
				when (hasFocus) {
					true ->
						activity?.window?.setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_VISIBLE)
					false ->
						activity?.window?.setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_HIDDEN)
				}
			}
			setOnEditorActionListener { textView, actionId, _ ->
				when (actionId) {
					IME_ACTION_DONE -> {
						val profileName = textView.text.toString()
						presenter.onEditTextDoneButtonClicked(profileName)
						textView.text = ""
						true
					}
					else -> false
				}
			}
		}
	}

	override fun startReposFragment(profileName: String) =
		runOnUiThread {
			presenter.startReposFragment(profileName)
		}

	override fun showSnackbar(message: String) =
		super.showSnackbar(rootStartFragment)(message)

}