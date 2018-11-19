package com.dobrowins.arrowktplayground.repository.cache

import arrow.core.Try
import arrow.syntax.function.andThen
import io.paperdb.Book
import io.paperdb.Paper

/**
 * @author Artem Dobrovinskiy
 */
abstract class PersistWorker {

    protected fun <T : Any> put(key: String, value: T) =
        put(key, value, null)

    protected fun <T : Any> put(key: String, value: T, bookName: String?) {
        val putFunc = getBook(bookName) andThen putValueByKey(key, value)
        putFunc.invoke()
    }

    protected fun <T : Any?> get(key: String, defaultValue: T): T =
        get(key, defaultValue, null)

    protected fun <T : Any?> get(key: String, defaultValue: T, bookName: String?): T =
        Try.invoke(getBook(bookName) andThen readByKey(key, defaultValue)).fold(
            ifFailure = {
                val deletionFunc = getBook(bookName) andThen deleteByKey(key)
                deletionFunc.invoke()
                defaultValue
            },
            ifSuccess = { it }
        )

    protected fun deleteByKey(key: String, bookName: String?): () -> Unit =
        getBook(bookName) andThen delete(key)

    protected fun deleteBook(bookName: String?) {
        bookName?.let {
            val deletionFunc = getBook(bookName) andThen deleteAll
            deletionFunc.invoke()
        }
    }

    private val deleteAll: (Book) -> Unit = { book -> book.destroy() }

    private fun getBook(bookName: String?): () -> Book = {
        if (bookName.isNullOrEmpty()) Paper.book()
        else Paper.book(bookName)
    }

    private fun deleteByKey(key: String): (Book) -> Unit =
        { book -> book.delete(key) }

    private fun putValueByKey(key: String, value: Any): (Book) -> Unit = { book ->
        book.write(key, value)
    }

    private fun <T> readByKey(key: String, defaultValue: T): (Book) -> T = { book ->
        book.read(key, defaultValue)
    }

    private fun delete(key: String): (Book) -> Unit = { book ->
        book.delete(key)
    }

}