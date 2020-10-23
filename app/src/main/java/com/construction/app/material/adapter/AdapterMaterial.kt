package com.construction.app.material.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.databinding.ViewMaterialBinding
import com.construction.app.material.model.Material
import javax.inject.Inject

class AdapterMaterial @Inject internal constructor(
) : RecyclerView.Adapter<AdapterMaterial.ViewHolder>() {
    private var collection = emptyList<Material>()

    inner class ViewHolder(val binding: ViewMaterialBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(collection: List<Material>) {
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewMaterialBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.material = document

        holder.binding.layoutMain.setOnClickListener {}
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
