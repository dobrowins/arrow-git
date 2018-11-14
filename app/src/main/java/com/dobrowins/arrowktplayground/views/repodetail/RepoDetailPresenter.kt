package com.dobrowins.arrowktplayground.views.repodetail

import arrow.syntax.function.forwardCompose
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.RepoDetailInteractor
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailPresenter @Inject constructor(
	private val repoDetailInteractor: RepoDetailInteractor,
	private val router: Router
) : BasePresenter<RepoDetailView>() {

	private val fetchReposJob = Job()
	private val uiScope = CoroutineScope(Dispatchers.Main + fetchReposJob)
	private val pairWithJob: (Throwable) -> Pair<Job, Throwable> = { t -> fetchReposJob to t }
	private val cancelJob: (Pair<Job, Throwable>) -> Throwable = { (job, throwable) ->
		job.cancel()
		throwable
	}

	fun fetchRepoData(repoId: String) =
		uiScope.launch {
			repoDetailInteractor.getRepoData(repoId)
				.fold(
					ifLeft = pairWithJob forwardCompose cancelJob forwardCompose viewState::displayError,
					ifRight = viewState::initView
				)
		}

	fun onNavigationIconClicked() = router.backTo(SCREEN_REPOS)

}
