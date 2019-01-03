package com.dobrowins.arrowgit.views.repos

import android.content.res.Resources
import arrow.core.Option
import arrow.core.toOption
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.base.BasePresenter
import com.dobrowins.arrowgit.base.adapters.HolderType
import com.dobrowins.arrowgit.domain.ReposViewInteractor
import com.dobrowins.arrowgit.domain.data.RepositoryData
import com.dobrowins.arrowgit.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowgit.navigation.SCREEN_START
import com.dobrowins.arrowgit.views.cancelJob
import com.dobrowins.arrowgit.views.mapThrowableMessage
import com.dobrowins.arrowgit.views.mapToErrorItem
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
    private val resources: Resources
) : BasePresenter<ReposView>() {

    fun loadData(profileName: String) {
        val fetchReposJob = Job()
        addJobToScope(fetchReposJob)
        launch {
            reposViewInteractor.fetchReposData(profileName).unsafeRunAsync {
                it.fold(
                    ifLeft = cancelJob(fetchReposJob) forwardCompose displayErrorItem,
                    ifRight = List<RepositoryData?>::filterNotNull
                            forwardCompose List<RepositoryData>::toOption
                            forwardCompose showItemsOrErrorIfNull
                )
            }
        }
    }

    fun onToolbarNavigationIconPressed() = router.backTo(SCREEN_START)

    fun onRepoItemClicked(repoName: String) = router.navigateTo(SCREEN_REPO_DETAIL, repoName)

    private val displayErrorItem =
        mapThrowableMessage forwardCompose mapToErrorItem forwardCompose viewState::showItems

    private val showItemsOrErrorIfNull: (Option<List<RepositoryData>>) -> Unit = {
        it.fold(
            ifEmpty = {
                resources.getString(R.string.error_no_items).let(viewState::showSnackbar)
                emptyList<HolderType>().let(viewState::showItems)
            },
            ifSome = mapToItems forwardCompose viewState::showItems
        )
    }

    private val mapToItems: (List<RepositoryData>) -> List<HolderType> = { responseRepos ->
        responseRepos.map { data ->
            RepoItem(
                id = data.id ?: resources.getString(R.string.default_id),
                name = data.name ?: resources.getString(R.string.default_name),
                fullName = data.fullName ?: resources.getString(R.string.default_full_name),
                htmlUrl = data.htmlUrl ?: resources.getString(R.string.default_url),
                description = data.description.orEmpty(),
                language = data.language.orEmpty(),
                forkedText = String.format(
                    resources.getString(R.string.format_string_item_forked_count),
                    data.forkedCount ?: 0
                ),
                starredText = String.format(
                    resources.getString(R.string.format_string_item_starred_count),
                    data.starredCount ?: 0
                )
            )
        }
    }

}
