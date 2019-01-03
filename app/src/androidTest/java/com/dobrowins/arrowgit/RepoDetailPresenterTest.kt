package com.dobrowins.arrowgit

import android.content.res.Resources
import com.dobrowins.arrowgit.domain.RepoDetailInteractor
import com.dobrowins.arrowgit.domain.RepoDetailInteractorImpl
import com.dobrowins.arrowgit.views.repodetail.RepoDetailPresenter
import com.dobrowins.arrowgit.views.repodetail.RepoDetailView
import com.dobrowins.arrowgit.views.repodetail.`RepoDetailView$$State`
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailPresenterTest {

    private lateinit var presenter: RepoDetailPresenter
    private lateinit var interactorMock: RepoDetailInteractor
    private lateinit var routerMock: Router
    private lateinit var resourcesMock: Resources
    private lateinit var reposViewMock: RepoDetailView
    private lateinit var viewStateMock: `RepoDetailView$$State`

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        interactorMock = Mockito.mock(RepoDetailInteractorImpl::class.java)
        routerMock = Mockito.mock(Router::class.java)
        resourcesMock = Mockito.mock(Resources::class.java)
        reposViewMock = Mockito.mock(RepoDetailView::class.java)
        viewStateMock = Mockito.mock(`RepoDetailView$$State`::class.java)
        presenter = RepoDetailPresenter(
            repoDetailInteractor = interactorMock,
            resources = resourcesMock,
            router = routerMock
        )
        presenter.attachView(reposViewMock)
        presenter.setViewState(viewStateMock)
        presenter.coroutineContext = Dispatchers.Default + SupervisorJob()
    }

    @Test
    fun onNavigationClickedTest() {
        presenter.onNavigationIconClicked()
        verify(routerMock, times(1)).backTo(anyString())
    }

    @Test
    fun fetchRepoDataTest() {
        // TODO: case where data is exists + case where fetching returns null
    }

}