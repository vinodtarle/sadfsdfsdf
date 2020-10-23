package com.construction.app.site.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.ViewSiteBinding
import com.construction.app.site.model.Site
import com.construction.app.site.view.FragmentSite
import javax.inject.Inject

class AdapterSite @Inject internal constructor(
) : RecyclerView.Adapter<AdapterSite.ViewHolder>() {
    private lateinit var baseFragment: BaseFragment
    private var collection = emptyList<Site>()

    inner class ViewHolder(val binding: ViewSiteBinding) : RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(baseFragment: BaseFragment, collection: List<Site>) {
        this.baseFragment = baseFragment
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewSiteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.site = document

        holder.binding.layoutMain.setOnClickListener {
            (baseFragment as FragmentSite).navigateToDetails(document = document)
        }
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
