package com.dobrowins.arrowktplayground.repository

/**
 * @author Artem Dobrovinskiy
 */
abstract class BaseRepository<T> {

    abstract fun fetchData(id: Any? = null): (Any) -> T

    abstract val cache: (T) -> T

    abstract val fetchCachedData: () -> T

    abstract val returnOnError: (Throwable) -> T

}