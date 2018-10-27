package com.dobrowins.arrowktplayground.repository.cache

import arrow.core.Option
import arrow.core.Try
import arrow.syntax.function.andThen
import io.paperdb.Book
import io.paperdb.Paper

/**
 * @author Artem Dobrovinskiy
 */
abstract class PersistWorker {

    private fun initBook(bookName: String?): () -> Book = {
        if (bookName.isNullOrEmpty()) Paper.book()
        else Paper.book(bookName)
    }

    private fun deleteByKey(key: String): (Book) -> Unit =
        { book -> book.delete(key) }

    private fun putValueByKey(key: String, value: Any): (Book) -> Unit = { book ->
        book.write(key, value)
    }

    protected fun <T : Any> put(key: String, value: T) =
        put(key, value, null)

    protected fun <T : Any> put(key: String, value: T?, bookName: String?): Unit =
        Option.fromNullable(value).fold(
            { initBook(bookName) andThen deleteByKey(key) },
            { v: T -> initBook(bookName) andThen putValueByKey(key, v) }
        )

    protected operator fun <T : Any> get(key: String, defaultValue: T): T =
        get(key, defaultValue, null)

    private fun <T> readByKey(key: String, defaultValue: T): (Book) -> T = { book ->
        book.read(key, defaultValue)
    }

    protected operator fun <T : Any> get(key: String, defaultValue: T, bookName: String?): T =
        Try.invoke(initBook(bookName) andThen readByKey(key, defaultValue)).fold(
            { t ->
                initBook(bookName) andThen deleteByKey(key)
                // TODO: test if they would be invoked!
                defaultValue
            },
            { it }
        )

    protected fun deleteByKey(key: String, bookName: String?): () -> Unit =
        initBook(bookName) andThen delete(key)

    private fun delete(key: String): (Book) -> Unit = { book ->
        book.delete(key)
    }

}