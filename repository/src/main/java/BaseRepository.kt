import arrow.Kind

/**
 * @author Artem Dobrovinskiy
 */

interface BaseRepository<H, T> {
    fun fetchFromRemoteById(id: String?): Kind<H, List<T>>
    fun fetchAllFromCache(): Kind<H, List<T>>
    fun fetchOneById(id: String?): Kind<H, T>
}
