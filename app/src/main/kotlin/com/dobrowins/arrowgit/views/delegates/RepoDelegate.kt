package com.dobrowins.arrowgit.views.delegates

import android.view.ViewGroup
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.base.AdapterDelegate
import com.dobrowins.arrowgit.base.adapters.BaseViewHolder
import com.dobrowins.arrowgit.views.repos.RepoItem
import kotlinx.android.synthetic.main.vh_item_repo.view.*

/**
 * @author Artem Dobrovinskiy
 */

class RepoDelegate(private val repoOnClickFunc: (String) -> Unit) : AdapterDelegate {
    override fun createViewHolder(parent: ViewGroup): BaseViewHolder =
        ReposItemVH(parent, repoOnClickFunc)
}

class ReposItemVH(itemView: ViewGroup, private val λ: (String) -> Unit) :
    BaseViewHolder(itemView, R.layout.vh_item_repo) {

    override fun bind(item: Any) = with(itemView) {
        item as RepoItem
        item.fullName.let(tvRepoItemTitle::setText)
        item.htmlUrl.let(tvRepoItemUrl::setText)
        item.language.let(tvRepoItemLanguage::setText)
//        item.forkedText.let(tvRepoItemForkedCount::setText)
//        item.starredText.let(tvRepoItemStarredCount::setText)
        rootRepoItem.setOnClickListener { λ(item.fullName) }
        return@with
    }

    override fun unbind() {
        with(itemView) {
            tvRepoItemTitle.text = ""
            tvRepoItemUrl.text = ""
            rootRepoItem.setOnClickListener(null)
        }
        super.unbind()
    }

}
