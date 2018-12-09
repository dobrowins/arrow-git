package com.dobrowins.arrowktplayground.views.repodetail

import arrow.core.Option
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.domain.RepoDetailInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.views.cancelJob
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class RepoDetailPresenter @Inject constructor(
	private val repoDetailInteractor: RepoDetailInteractor,
	private val router: Router,
	private val dispatchersProvider: DispatchersProvider
) : BasePresenter<RepoDetailView>() {

	private val fetchReposJob = Job()
	private val uiScope = CoroutineScope(dispatchersProvider.main + fetchReposJob)

	fun fetchRepoData(repoName: String) {
		uiScope.launch {
			repoDetailInteractor.getRepoData(repoName).unsafeRunAsync { either ->
				either.fold(
                    ifLeft = cancelJob(fetchReposJob) forwardCompose composeMessageAndShowSnackbar,
                    ifRight = mapToOption forwardCompose showItemsOrErrorIfNull
				)
			}
		}
	}

	fun onNavigationIconClicked() = router.backTo(SCREEN_REPOS)

    private val composeMessageAndShowSnackbar =
        mapThrowableMessage forwardCompose viewState::showSnackbar

	private val mapToOption: (RepositoryData?) -> Option<RepositoryData> =
		{ Option.fromNullable(it) }

	private val showItemsOrErrorIfNull: (Option<RepositoryData>) -> Unit = {
		it.fold(
			ifEmpty = { viewState.showSnackbar("No data to display") },
			ifSome = viewState::initView
		)
	}

}
