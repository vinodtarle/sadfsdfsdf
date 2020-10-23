package com.construction.app.unit.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.base.repository.Resource
import com.construction.app.base.view.BaseFragment
import com.construction.app.unit.viewmodel.ViewModelUnit

class FragmentUnitDetails : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_unit_details
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Unit Details"

    private lateinit var viewModel: ViewModelUnit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelUnit::class.java)
    }

    private fun initObserver() {
        this.viewModel.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        updateCollection()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showFullScreenError()
                    }
                }
            })
    }

    private fun updateCollection() {


    }
}
