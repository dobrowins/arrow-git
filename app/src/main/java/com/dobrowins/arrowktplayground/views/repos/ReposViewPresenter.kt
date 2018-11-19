package com.dobrowins.arrowktplayground.views.repos

import arrow.core.Option
import arrow.core.toOption
import arrow.syntax.function.andThen
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowktplayground.navigation.SCREEN_START
import com.dobrowins.arrowktplayground.views.cancelJob
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
class ReposViewPresenter @Inject constructor(
	private val router: Router,
	private val reposViewInteractor: ReposViewInteractor,
	private val dispatchersProvider: DispatchersProvider
) : BasePresenter<ReposView>() {

	private val fetchReposJob = Job()
	private val viewScope = CoroutineScope(dispatchersProvider.main + fetchReposJob)

	fun loadData(profileName: String) {
		viewScope.launch {
			val reposIO = withContext(dispatchersProvider.io) {
				reposViewInteractor.fetchReposData(profileName)
			}
			reposIO.unsafeRunAsync { repos ->
				repos.fold(
					ifLeft = cancelJob(fetchReposJob) andThen mapThrowableMessage andThen viewState::showSnackbar,
					ifRight = mapToOption andThen showItemsOrErrorIfNull
				)
			}
		}
	}

	fun onToolbarNavigationIconPressed() = router.navigateTo(SCREEN_START)

	fun onRepoItemClicked(repoName: String) = router.navigateTo(SCREEN_REPO_DETAIL, repoName)

	private val mapToOption: (List<RepositoryData?>?) -> Option<List<RepositoryData>> =
		{ it?.filterNotNull().toOption() }

	private val showItemsOrErrorIfNull: (Option<List<RepositoryData>>) -> Unit = {
		it.fold(
			ifEmpty = { viewState.showSnackbar("No items to display") },
			ifSome = mapToItems andThen viewState::showRepos
		)
	}

	private val mapToItems: (List<RepositoryData>) -> List<RepoItem> = { responseRepos ->
		responseRepos.map { data ->
			RepoItem(
				id = data.id,
				name = data.name,
				fullName = data.fullName,
				htmlUrl = data.htmlUrl,
				description = data.description
			)
		}
	}

}
