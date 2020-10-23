package com.construction.app.attendance.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.construction.app.attendance.model.Attendance
import com.construction.app.databinding.ViewAttendanceBinding
import javax.inject.Inject

class AdapterAttendance @Inject internal constructor(
) : RecyclerView.Adapter<AdapterAttendance.ViewHolder>() {
    private var collection = emptyList<Attendance>()

    class ViewHolder(val binding: ViewAttendanceBinding) :
        RecyclerView.ViewHolder(binding.layoutMain)

    internal fun setCollection(collection: List<Attendance>) {
        this.collection = collection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewAttendanceBinding.inflate(inflater, parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val document = this.collection[position]
        holder.binding.attendance = document
        holder.binding.layoutMain.setOnClickListener {
            //  if (this.context is ActivityAttendance)
            //      this.context.addAttendance(data)
        }
    }

    override fun getItemCount(): Int {
        return this.collection.size
    }
}
