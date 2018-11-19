package com.dobrowins.arrowktplayground.repository

/**
 * @author: Artem Dobrovinsky
 */
fun <T> filterList(): ((T) -> Boolean, List<T>) -> List<T> = {
	f, list -> list.filter { f(it) }
}
