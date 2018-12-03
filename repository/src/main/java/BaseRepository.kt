import arrow.Kind

/**
 * @author Artem Dobrovinskiy
 */

interface BaseRepository<H, T> {
    fun fetchFromRemoteById(id: String?): Kind<H, List<T>>
    fun fetchAllFromCache(): Kind<H, List<T>>
    fun fetchOneById(id: String?): Kind<H, T>
}


class RemoteRepository<H, RepositoryDataResponse> : BaseRepository<H, RepositoryDataResponse> {

    override fun fetchFromRemoteById(id: String?): Kind<H, List<RepositoryDataResponse>> =
        TODO()

    override fun fetchAllFromCache(): Kind<H, List<RepositoryDataResponse>> {
        TODO()
    }

    override fun fetchOneById(id: String?): Kind<H, RepositoryDataResponse> {
        TODO()
    }
}