package com.dobrowins.arrowktplayground.base.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Artem Dobrovinskiy
 */
abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(parent: ViewGroup, layoutId: Int) : super(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )

    constructor(view: View) : super(view)

    abstract fun bind(item: Any)
    open fun unbind() {
    }

}