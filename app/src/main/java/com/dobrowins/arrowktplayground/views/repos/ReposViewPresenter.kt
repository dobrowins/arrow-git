package com.dobrowins.arrowktplayground.views.repos

import android.content.Context
import arrow.core.Option
import arrow.core.toOption
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowktplayground.navigation.SCREEN_START
import com.dobrowins.arrowktplayground.views.cancelJob
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class ReposViewPresenter @Inject constructor(
    private val router: Router,
    private val reposViewInteractor: ReposViewInteractor,
    private val context: Context
) : BasePresenter<ReposView>() {

    fun loadData(profileName: String) {
        val fetchReposJob = Job()
        addJobToScope(fetchReposJob)
        launch {
            reposViewInteractor.fetchReposData(profileName).unsafeRunAsync {
                it.fold(
                    ifLeft = cancelJob(fetchReposJob) forwardCompose displayErrorMessage,
                    ifRight = mapToOption forwardCompose showItemsOrErrorIfNull
                )
            }
        }
    }

    fun onToolbarNavigationIconPressed() = router.navigateTo(SCREEN_START)

    fun onRepoItemClicked(repoName: String) = router.navigateTo(SCREEN_REPO_DETAIL, repoName)

    private val displayErrorMessage = mapThrowableMessage forwardCompose viewState::showSnackbar

    private val mapToOption: (List<RepositoryData?>?) -> Option<List<RepositoryData>> =
        { it?.filterNotNull().toOption() }

    private val showItemsOrErrorIfNull: (Option<List<RepositoryData>>) -> Unit = {
        it.fold(
            ifEmpty = { viewState.showSnackbar("No items to display") },
            ifSome = mapToItems forwardCompose viewState::showRepos
        )
    }

    private val mapToItems: (List<RepositoryData>) -> List<RepoItem> = { responseRepos ->
        responseRepos.map { data ->
            RepoItem(
                id = data.id ?: "unknown id",
                name = data.name ?: "unknown name",
                fullName = data.fullName ?: "unknown full name",
                htmlUrl = data.htmlUrl ?: "unknown url",
                description = data.description.orEmpty(),
                language = data.language.orEmpty(),
                forkedText = String.format(
                    context.getString(R.string.format_string_item_forked_count),
                    data.forkedCount ?: 0
                ),
                starredText = String.format(
                    context.getString(R.string.format_string_item_starred_count),
                    data.starredCount ?: 0
                )
            )
        }
    }

}
