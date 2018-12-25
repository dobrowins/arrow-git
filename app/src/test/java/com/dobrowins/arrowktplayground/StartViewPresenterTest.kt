package com.dobrowins.arrowktplayground

import arrow.data.invalid
import arrow.data.valid
import com.dobrowins.arrowktplayground.domain.StartViewInteractor
import com.dobrowins.arrowktplayground.domain.StartViewInteractorImpl
import com.dobrowins.arrowktplayground.views.start.StartView
import com.dobrowins.arrowktplayground.views.start.StartViewPresenter
import com.dobrowins.arrowktplayground.views.start.`StartView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class StartViewPresenterTest {

    private lateinit var interactorMock: StartViewInteractor
    private lateinit var routerMock: Router
    private lateinit var presenter: StartViewPresenter
    private lateinit var startViewMock: StartView
    private lateinit var viewState: `StartView$$State`

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        interactorMock = mock(StartViewInteractorImpl::class.java)
        routerMock = mock(Router::class.java)
        startViewMock = mock(StartView::class.java)
        viewState = mock(`StartView$$State`::class.java)
        presenter = StartViewPresenter(
            interactorMock,
            routerMock
        )
        presenter.attachView(startViewMock)
        presenter.setViewState(viewState)
    }

    @Test
    fun `onEditTextDoneButtonClicked shows error if input is empty`() {
        `when`(interactorMock.validateInput(STRING_EMPTY))
            .thenReturn(Exception("Input is invalid").invalid())
        presenter.onEditTextDoneButtonClicked(STRING_EMPTY)
        verify(presenter.viewState, times(1)).showSnackbar(anyString())
        verify(presenter.viewState, never()).startReposFragment(anyString())
    }

    @Test
    fun `onEditTextDoneButtonClicked starts reposFragment if input is not empty`() {
        `when`(interactorMock.validateInput(STRING_DUMMY))
            .thenReturn(STRING_DUMMY.valid())
        presenter.onEditTextDoneButtonClicked(STRING_DUMMY)
        verify(presenter.viewState, times(1)).startReposFragment(STRING_DUMMY)
        verify(presenter.viewState, never()).showSnackbar(anyString())
    }

    companion object {
        private const val STRING_EMPTY = ""
        private const val STRING_DUMMY = "dobrowins"
    }

}