package com.dobrowins.arrowktplayground.views.repos

import android.view.ViewGroup
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.base.AdapterDelegate
import com.dobrowins.arrowktplayground.base.BaseAdapter
import com.dobrowins.arrowktplayground.base.adapters.BaseViewHolder
import com.dobrowins.arrowktplayground.base.adapters.HolderType
import kotlinx.android.synthetic.main.vh_item_repo.view.*

/**
 * @author Artem Dobrovinskiy
 */
class ReposAdapter(
    repoOnClickFunc: () -> Unit
) : BaseAdapter() {

    init {
        delegates.add(HolderType.REPO, RepoDelegate(repoOnClickFunc))
    }

}

class ReposItemVH(itemView: ViewGroup, λ: () -> Unit) :
    BaseViewHolder(itemView, R.layout.vh_item_repo) {
    override fun bind(item: Any) = with(itemView) {
        item as RepoItem
        item.fullName.let(tvRepoItemTitle::setText)
        item.htmlUrl.let(tvRepoItemUrl::setText)
        return@with
    }

    override fun unbind() {
        with(itemView) {
            tvRepoItemTitle.text = ""
            tvRepoItemUrl.text = ""
        }
        super.unbind()
    }
}

class RepoDelegate(private val repoOnClickFunc: () -> Unit) : AdapterDelegate {
    override fun createViewHolder(parent: ViewGroup): BaseViewHolder =
        ReposItemVH(parent, repoOnClickFunc)
}