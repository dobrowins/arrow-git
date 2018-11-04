package com.dobrowins.arrowktplayground.views.repos

import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
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
        reposViewInteractor.fetchReposData(repoId).map(mapToItems)

    fun onToolbarNavigationIconPressed() = router.exit()

    private val mapToItems: (List<RepositoryData>) -> List<RepoItem> = { responseRepos ->
        val mapped = responseRepos.map {
            RepoItem(
                it.id,
                it.name,
                it.fullName,
                it.htmlUrl,
                it.description
            )
        }
        mapped
    }

}
