package com.dobrowins.arrowktplayground.views.start

import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.base.BaseView
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
interface StartView : BaseView

class StartFragment : BaseFragment(), StartView {

    @Inject
    @InjectPresenter
    lateinit var presenter: StartViewPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override val layoutId: Int = R.layout.fragment_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        presenter.onEditTextDoneButtonClicked(profileName).fold(
                            ifLeft = mapThrowableMessage forwardCompose showSnackbar(
                                rootStartFragment
                            ),
                            ifRight = startReposFragment
                        )
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private val startReposFragment: (String) -> Unit = { profileName ->
        runOnUiThread {
            presenter.startReposFragment(profileName)
        }
    }

}