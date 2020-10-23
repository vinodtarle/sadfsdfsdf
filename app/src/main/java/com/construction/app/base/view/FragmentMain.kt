package com.construction.app.base.view

import android.os.Bundle
import android.view.View
import com.construction.app.R
import com.construction.app.base.utility.*
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_main
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "My Construction"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        setTitle()
        homeBackButton(visible = false)
        homeOptionMenu(visible = true)
    }

    private fun init() {
        site.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentSite()
            )
        }
        payment.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentPayment()
            )
        }
        supplier.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentSupplier()
            )
        }
        material.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentMaterial()
            )
        }
        labour.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentLabour()
            )
        }
        attendance.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMainDirections.toFragmentAttendance()
            )
        }
    }
}

