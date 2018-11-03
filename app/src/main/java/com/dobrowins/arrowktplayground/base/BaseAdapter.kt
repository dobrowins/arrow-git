package com.dobrowins.arrowktplayground.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dobrowins.arrowktplayground.base.adapters.BaseViewHolder
import com.dobrowins.arrowktplayground.base.adapters.HolderType

/**
 * @author Artem Dobrovinskiy
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    val items = mutableListOf<HolderType>()
    protected val delegates = mutableListOf<AdapterDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BaseViewHolder =
        delegates[type].createViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(items[position])

    override fun onViewRecycled(holder: BaseViewHolder) = holder.unbind()

    override fun getItemViewType(position: Int): Int = items[position].type()

    fun add(input: List<HolderType>) {
        val currentSize = items.size
        items.addAll(input)
        notifyItemRangeInserted(currentSize, items.size)
    }

    fun replace(input: List<HolderType>) {
        val oldSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, oldSize)
        items.addAll(input)
        notifyItemRangeInserted(0, items.size)
    }

}

interface AdapterDelegate {
    fun createViewHolder(parent: ViewGroup): BaseViewHolder
}
