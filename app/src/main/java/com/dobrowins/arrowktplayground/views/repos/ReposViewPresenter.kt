package com.dobrowins.arrowktplayground.views.repos

import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_START
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

    fun loadData(profileName: String) =
        reposViewInteractor.fetchReposData(profileName).map(mapToItems)

    fun onToolbarNavigationIconPressed() = router.navigateTo(SCREEN_START)

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
