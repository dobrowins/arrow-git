package com.dobrowins.arrowgit

import android.content.res.Resources
import arrow.effects.IO
import com.dobrowins.arrowgit.domain.ReposViewInteractor
import com.dobrowins.arrowgit.domain.ReposViewInteractorImpl
import com.dobrowins.arrowgit.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowgit.navigation.SCREEN_START
import com.dobrowins.arrowgit.views.repos.ReposView
import com.dobrowins.arrowgit.views.repos.ReposViewPresenter
import com.dobrowins.arrowgit.views.repos.`ReposView$$State`
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

/**
 * @author Artem Dobrovinskiy
 */
class ReposViewPresenterTest {

    private lateinit var presenter: ReposViewPresenter
    private lateinit var interactorMock: ReposViewInteractor
    private lateinit var routerMock: Router
    private lateinit var resourcesMock: Resources
    private lateinit var reposViewMock: ReposView
    private lateinit var viewStateMock: `ReposView$$State`

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        interactorMock = mock(ReposViewInteractorImpl::class.java)
        routerMock = mock(Router::class.java)
        resourcesMock = mock(Resources::class.java)
        reposViewMock = mock(ReposView::class.java)
        viewStateMock = mock(`ReposView$$State`::class.java)
        presenter = ReposViewPresenter(
            router = routerMock,
            reposViewInteractor = interactorMock,
            resources = resourcesMock
        )
        presenter.attachView(reposViewMock)
        presenter.setViewState(viewStateMock)
        presenter.coroutineContext = Dispatchers.Default + SupervisorJob()
    }

    // onToolbarNavigationPressed navigates to start screen
    @Test
    fun onToolbarNavigationPressed_navigates_to_start_screen() {
        presenter.onToolbarNavigationIconPressed()
        verify(routerMock, times(1)).backTo(SCREEN_START)
        verify(routerMock, never()).navigateTo(anyString())
    }

    // onRepoItemClicked navigates to detail screen
    @Test
    fun onRepoItemClicked_navigates_to_detail_screen() {
        presenter.onRepoItemClicked(STRING_REPO)
        verify(routerMock, times(1)).navigateTo(SCREEN_REPO_DETAIL, eq(STRING_REPO))
        verify(routerMock, never()).backTo(anyString())
    }

    // loadData shows error item if error occured while fetching repos data
    @Test
    fun loadData_shows_error() {
        presenter.loadData(STRING_ERROR)
        verify(presenter.viewState, times(1)).showItems(anyList())
    }

    // loadData displays no items if repos data fetch returned null
    @Test
    fun loadDataTest_shows_nothing_if_null() {
        runBlocking {
            `when`(interactorMock.fetchReposData(STRING_NULL))
                .thenReturn(IO.raiseError(RuntimeException()))
            presenter.loadData(STRING_ERROR)
            verify(viewStateMock, times(1)).showItems(anyList())
            verify(viewStateMock, times(1)).showSnackbar(anyString())
        }
    }

    // loadData displays items if repos data fetch were successful
    @Test
    fun loadDataTest_shows_items() {
        runBlocking {
            `when`(interactorMock.fetchReposData(STRING_SUCCESS))
                .thenReturn(IO.raiseError(RuntimeException()))
            presenter.loadData(STRING_SUCCESS)
            verify(viewStateMock, times(1)).showItems(anyList())
        }
    }

    companion object {
        private const val STRING_REPO = "repo"
        private const val STRING_ERROR = "ERROR"
        private const val STRING_NULL = "NULL"
        private const val STRING_SUCCESS = "SUCCESS"
    }

}
