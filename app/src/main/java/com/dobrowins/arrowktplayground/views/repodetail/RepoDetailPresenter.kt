package com.dobrowins.arrowktplayground.views.repodetail

import arrow.core.Option
import arrow.syntax.function.andThen
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.DispatchersProvider
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.RepoDetailInteractor
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
	private val pairWithJob: (Throwable) -> Pair<Job, Throwable> = { t -> fetchReposJob to t }
	private val cancelJob: (Pair<Job, Throwable>) -> Throwable = { (job, throwable) ->
		job.cancel()
		throwable
	}
	private val cancelJobOnError = pairWithJob andThen cancelJob
	private val composeMessageAndShowSnackbar = mapThrowableMessage andThen viewState::showSnackbar

	fun fetchRepoData(repoName: String) {
		uiScope.launch {
			val getRepoIO = withContext(dispatchersProvider.io) { repoDetailInteractor.getRepoData(repoName) }
			getRepoIO.unsafeRunAsync { either ->
				either.fold(
					ifLeft = cancelJobOnError andThen composeMessageAndShowSnackbar,
					ifRight = {
						Option.fromNullable(it).fold(
							ifEmpty = {
								// TODO: return and show fullscreen error view
								mapThrowableMessage(IllegalStateException("fetched no data for repository"))
									.let(viewState::showSnackbar)
							},
							ifSome = viewState::initView
						)
					}
				)
			}
		}
	}

	fun onNavigationIconClicked() = router.backTo(SCREEN_REPOS)

}
