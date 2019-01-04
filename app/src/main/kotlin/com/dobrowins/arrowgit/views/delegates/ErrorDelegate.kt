package com.dobrowins.arrowgit.views.delegates

import android.view.ViewGroup
import arrow.core.toOption
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.base.AdapterDelegate
import com.dobrowins.arrowgit.base.adapters.BaseViewHolder
import com.dobrowins.arrowgit.views.repos.ErrorItem
import kotlinx.android.synthetic.main.vh_item_error.view.*

/**
 * @author Artem Dobrovinskiy
 */
class ErrorDelegate(private val errorOnClickFunc: () -> Unit) : AdapterDelegate {
    override fun createViewHolder(parent: ViewGroup): BaseViewHolder =
        ErrorItemVH(parent, errorOnClickFunc)
}

class ErrorItemVH(itemView: ViewGroup, private val λ: () -> Unit) :
    BaseViewHolder(itemView, R.layout.vh_item_error) {

    override fun bind(item: Any) = with(itemView) {
        item as ErrorItem
        tvErrorItemVHTitle.text = item.message.toOption().fold(
            ifEmpty = { resources.getString(R.string.error_unknown) },
            ifSome = { message -> resources.getString(R.string.error_view_title, message) }
        )
        return@with
    }

}