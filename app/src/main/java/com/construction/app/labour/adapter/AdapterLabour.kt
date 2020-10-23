package com.construction.app.labour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.ViewLabourBinding
import com.construction.app.labour.model.Labour
import com.construction.app.labour.view.FragmentLabour
import javax.inject.Inject

class AdapterLabour @Inject internal constructor(
) : RecyclerView.Adapter<AdapterLabour.ViewHolder>() {
    private lateinit var baseFragment: BaseFragment
    private var collection = emptyList<Labour>()

    inner class ViewHolder(val binding: ViewLabourBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(baseFragment: BaseFragment, collection: List<Labour>) {
        this.baseFragment = baseFragment
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewLabourBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.labour = document
        holder.binding.layoutMain.setOnClickListener {
            (baseFragment as FragmentLabour).navigateToDetails(document = document)
        }
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
