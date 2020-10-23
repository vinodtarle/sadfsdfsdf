package com.construction.app.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.databinding.ViewPaymentBinding
import com.construction.app.payment.model.Payment
import javax.inject.Inject

class AdapterPayment @Inject internal constructor(
) : RecyclerView.Adapter<AdapterPayment.ViewHolder>() {
    private var collection = emptyList<Payment>()

    inner class ViewHolder(val binding: ViewPaymentBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(collection: List<Payment>) {
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPaymentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.payment = document
        holder.binding.layoutMain.setOnClickListener {
            //if (this.context is ActivityPayment)
            //   this.context.viewDetails(data)
//            else if (this.context is ActivitySite) {
//                this.context.viewDetails(data)
//            }
        }
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
