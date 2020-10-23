package com.construction.app.labour.view


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.homeBackButton
import com.construction.app.base.utility.homeOptionMenu
import com.construction.app.base.utility.setTitle
import com.construction.app.base.view.BaseFragment
import com.construction.app.labour.viewmodel.ViewModelLabour

class FragmentLabourDetails : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_labour_details
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Labour Details"

    private lateinit var viewModel: ViewModelLabour

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        setTitle(R.string.titleLabourDetails)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelLabour::class.java)
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
