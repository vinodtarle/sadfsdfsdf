package com.construction.app.report.view

import android.os.Bundle
import android.view.View
import com.construction.app.R
import com.construction.app.attendance.view.FragmentAttendance
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.utility.homeBackButton
import com.construction.app.base.utility.homeOptionMenu
import com.construction.app.base.view.BaseFragment
import com.construction.app.material.view.FragmentMaterial
import com.construction.app.payment.view.FragmentPayment
import com.construction.app.report.adapter.AdapterViewPager
import com.construction.app.site.model.Site
import com.construction.app.supplier.model.Supplier
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_details.*

class FragmentDetails : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_details
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Details"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        arguments?.let {
            val adapter = AdapterViewPager(
                fragmentManager = childFragmentManager,
                fragments = arrayOf(
                    when (val document = it.getSerializable("document")) {
                        is Site -> {
                            FragmentPayment(isReport = true, isSite = true, document = document)
                            FragmentMaterial(isReport = true, isSite = true, document = document)
                        }
                        is Supplier -> {
                            FragmentPayment(isReport = true, isSupplier = true, document = document)
                            FragmentMaterial(isReport = true, isSupplier = true, document = document)
                        }
                        else -> {
                            FragmentPayment(isReport = true, isLabour = true, document = document as BaseModule)
                            FragmentAttendance(isReport = true, document = document)
                        }
                    }
                ),
                titles = arrayOf(getString(R.string.strPayment), getString(R.string.strAttendance))
            )
            viewPager.adapter = adapter
            initListener()
        }
    }

    private fun initListener() {
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        )
        tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
