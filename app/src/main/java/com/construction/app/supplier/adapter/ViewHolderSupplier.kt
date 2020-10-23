package com.construction.app.supplier.adapter

import android.view.View
import com.construction.app.R
import com.construction.app.base.view.BaseFragment
import com.construction.app.base.view.BaseViewHolder
import com.construction.app.item.model.Item

class ViewHolderSupplier(
    val fragment: BaseFragment,
    val view: View
) : BaseViewHolder(fragment, view) {

    override fun bind(data: Item) {
    }

    override fun layoutResourceId() = R.layout.view_item
}