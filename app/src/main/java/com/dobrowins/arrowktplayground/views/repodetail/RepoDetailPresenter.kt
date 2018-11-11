package com.dobrowins.arrowktplayground.views.repodetail

import arrow.core.Either
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.RepoDetailInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailPresenter @Inject constructor(
    private val repoDetailInteractor: RepoDetailInteractor,
    private val router: Router
) : BasePresenter<RepoDetailView>() {

    /**
     * TODO:
     * view expects RepositoryData
     * presenter expects Either(showError, initView)
     * interactor expects IO (throw exception if empty, provide data if not)
     * repository requests Deffered<RepositoryDataResponse>
     */

    val fetchRepoData: (String) -> Either<Throwable, RepositoryData> =
        TODO("getEither(showErrorItem, display repo details")

    fun onNavigationIconClicked() = router.backTo(SCREEN_REPOS)

}
