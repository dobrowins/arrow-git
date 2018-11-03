package com.dobrowins.repository

import com.dobrowins.arrowktplayground.repository.cache.PersistWorker
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Artem Dobrovinskiy
 */
class PersistWorkerTest {

    private interface TestCache {
        fun putInt(n: Int)
        fun deleteCache(bookName: String?)
        fun getCachedInt(): Int
    }

    private val testPersistWorker = object : PersistWorker(), TestCache {
        override fun putInt(n: Int) = put(testIntKey, n, testBookName)
        override fun getCachedInt(): Int = get(testIntKey, 0, testBookName)
        override fun deleteCache(bookName: String?) = deleteBook(bookName)
    }

    private val testBookName = "test book name"
    private val testIntKey = "test int key"

    @Test
    fun putTest() {
        testPersistWorker.putInt(1)
        val cachedInt = testPersistWorker.getCachedInt()
        assertEquals(1, cachedInt)
    }

    @After
    fun destroy() {
        testPersistWorker.deleteCache(testBookName)
    }



}
