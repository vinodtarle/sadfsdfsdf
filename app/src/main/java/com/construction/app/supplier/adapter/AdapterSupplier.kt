package com.construction.app.supplier.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.ViewSupplierBinding
import com.construction.app.supplier.model.Supplier
import com.construction.app.supplier.view.FragmentSupplier
import javax.inject.Inject

class AdapterSupplier @Inject internal constructor(
) : RecyclerView.Adapter<AdapterSupplier.ViewHolder>() {
    private lateinit var baseFragment: BaseFragment
    private var collection = emptyList<Supplier>()

    inner class ViewHolder(val binding: ViewSupplierBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(baseFragment: BaseFragment, collection: List<Supplier>) {
        this.baseFragment = baseFragment
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewSupplierBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.supplier = document

        holder.binding.layoutMain.setOnClickListener {
            (baseFragment as FragmentSupplier).navigateToDetails(document = document)
        }
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
