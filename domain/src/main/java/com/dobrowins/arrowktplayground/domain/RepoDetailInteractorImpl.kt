package com.dobrowins.arrowktplayground.domain

import arrow.core.Either
import arrow.core.Right
import arrow.core.Try
import arrow.core.left
import arrow.core.right
import arrow.core.rightIfNotNull
import arrow.core.toOption
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.instances.`try`.monadError.monadError
import arrow.typeclasses.MonadErrorContinuation
import arrow.typeclasses.bindingCatch
import com.dobrowins.arrowktplayground.domain.data.GitHubRepository
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
class RepoDetailInteractorImpl @Inject constructor(
	private val gitHubRepository: GitHubRepository
) : RepoDetailInteractor {

	override suspend fun getRepoData(repoId: String?): Either<Throwable, RepositoryData> =
		Try { gitHubRepository.getRepositoryFromCache(repoId).await() }
			.fold(
				ifFailure = { Either.Left(it) },
				ifSuccess = { repositoryData ->
					repositoryData.toOption().fold(
						ifEmpty = { Either.Left(IllegalArgumentException("can't process Repository data that is null")) },
						ifSome = { nonNullData -> Either.right(nonNullData) }
					)
				}
			)

}
