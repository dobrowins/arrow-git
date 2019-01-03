package com.dobrowins.arrowgit.views.repodetail

import android.content.res.Resources
import arrow.core.Option
import arrow.core.toOption
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.base.BasePresenter
import com.dobrowins.arrowgit.domain.RepoDetailInteractor
import com.dobrowins.arrowgit.domain.data.RepositoryData
import com.dobrowins.arrowgit.navigation.SCREEN_REPOS
import com.dobrowins.arrowgit.views.cancelJob
import com.dobrowins.arrowgit.views.mapThrowableMessage
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
    private val resources: Resources,
    private val router: Router
) : BasePresenter<RepoDetailView>() {

    fun fetchRepoData(repoName: String) {
        val fetchReposJob = Job()
        addJobToScope(fetchReposJob)
        launch {
            repoDetailInteractor.getRepoData(repoName).unsafeRunAsync {
                it.fold(
                    ifLeft = cancelJob(fetchReposJob) forwardCompose composeMessageAndShowSnackbar,
                    ifRight = RepositoryData?::toOption forwardCompose showItemsOrErrorIfNull
                )
            }
        }
    }

    fun onNavigationIconClicked() = router.backTo(SCREEN_REPOS)

    private val composeMessageAndShowSnackbar =
        mapThrowableMessage forwardCompose viewState::showSnackbar

    private val showItemsOrErrorIfNull: (Option<RepositoryData>) -> Unit = {
        it.fold(
            ifEmpty = {
                resources.getString(R.string.error_no_data).let(viewState::showSnackbar)
            },
            ifSome = viewState::initView
        )
    }

}
