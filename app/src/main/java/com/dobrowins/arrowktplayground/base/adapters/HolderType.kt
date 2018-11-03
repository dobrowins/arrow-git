package com.dobrowins.arrowktplayground.base.adapters

/**
 * @author Artem Dobrovinskiy
 */
interface HolderType {
    fun type(): Int

    companion object {
        const val REPO = 0
    }
}