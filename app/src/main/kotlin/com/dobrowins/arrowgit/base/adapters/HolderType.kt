package com.dobrowins.arrowgit.base.adapters

/**
 * @author Artem Dobrovinskiy
 */
interface HolderType {
    fun type(): Int

    companion object {
        const val REPO = 0
        const val ERROR = 1
    }
}