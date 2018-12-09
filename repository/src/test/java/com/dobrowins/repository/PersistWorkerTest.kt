package com.dobrowins.repository

import org.junit.After
import org.junit.Test

/**
 * @author Artem Dobrovinskiy
 */
class GitHubPersistWorkerTest {

    // TODO: also test all the functions in base class!

    private val testBookName = "test book name"
    private val testIntKey = "test int key"

    @Test
    fun `put caches data if no data has been stored`() {
    }

    @Test
    fun `put overwrites data if some has been stored previously`() {
    }

    @Test
    fun `getRepositoryFromCache - returns first not null if response is cached`() {
    }

    @Test
    fun `getRepositoryFromCache - returns null if no data has been stored`() {
    }

    @After
    fun destroy() {
    }

}
