package com.dobrowins.arrowktplayground.repository.cache

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import arrow.core.Option
import arrow.core.compose
import arrow.syntax.function.andThen
import arrow.syntax.function.forwardCompose
import io.paperdb.Book
import io.paperdb.Paper

/**
 * @author Artem Dobrovinskiy
 */
abstract class PersistWorker {

    private val book: (String?) -> Book = { bookName ->
        if (bookName.isNullOrEmpty()) Paper.book()
        else Paper.book(bookName)
    }

    protected fun <T : Any> put(key: String, value: T) =
        put(key, value, null)

    private val deleteByKey: (Pair<Book, String>) -> Unit =
        { (book, key) -> book.delete(key) }

    private val writeByKey: (Triple<Book, String, Any?>) -> Unit =
        { (book, key, value) -> book.write(key, value) }

    protected fun <T : Any> put(key: String, value: T?, bookName: String?) {
        val pairWithKey: (Book) -> Pair<Book, String> = { book.invoke(bookName) to key }
        val pairWithValue: (Pair<Book, String>) -> Triple<Book, String, T?> = { (book, key) ->
            Triple(book, key, value)
        }
        Option.fromNullable(value).fold(
            {
                book andThen pairWithKey andThen deleteByKey
            },
            {
                book andThen pairWithKey andThen pairWithValue andThen writeByKey
                // TODO: that's same old imperative programming, fix this
            }
        )
    }

    protected operator fun <T : Any> get(key: String, defaultValue: T): T {
        return get(key, defaultValue, null)
    }

    protected operator fun <T : Any> get(key: String, defaultValue: T, bookName: String?): T {
        var result: T
        try {
            result = book.invoke(bookName).read(key, defaultValue)
        } catch (e: Exception) {
            delete(key, bookName)
            result = defaultValue
        }

        return result
    }

    protected fun delete(key: String) {
        delete(key, null)
    }

    protected fun delete(key: String, bookName: String?) {
        book.invoke(bookName).delete(key)
    }


}