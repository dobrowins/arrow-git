package com.dobrowins.arrowktplayground.views.repos

import arrow.core.Either
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.base.adapters.HolderType
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class ReposViewPresenter @Inject constructor(
    private val router: Router,
    private val reposViewInteractor: ReposViewInteractor
) : BasePresenter<ReposView>() {

    fun loadData(repoId: String) =
        reposViewInteractor.fetchReposData(repoId)
            .unsafeRunAsync(showRepos)

    fun onToolbarNavigationIconPressed() = router.exit()

    private val showRepos: (Either<Throwable, List<RepositoryData>>) -> Unit = {
        it.fold(
            ifLeft = showEmptyList,
            ifRight = mapToItems forwardCompose viewState::showRepos
        )
    }

    private val showEmptyList: (Throwable) -> Unit = { throwable ->
        throwable.message.orEmpty().let(viewState::showToast)
        viewState.showRepos(emptyList())
    }

    private val mapToItems: (List<RepositoryData>) -> List<RepoItem> = { responseRepos ->
        responseRepos.map {
            RepoItem(
                it.id,
                it.name,
                it.fullName,
                it.htmlUrl,
                it.description
            )
        }
    }

}

data class RepoItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val description: String
) : HolderType {
    override fun type(): Int = HolderType.REPO
}