package com.construction.app.base.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.item.model.Item

abstract class BaseViewHolder(
    baseFragment: BaseFragment,
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: Item)

    abstract fun layoutResourceId(): Int
}