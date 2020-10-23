package com.construction.app.unit.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.repository.Resource
import com.construction.app.base.view.BaseFragment
import com.construction.app.unit.adapter.AdapterUnit
import com.construction.app.unit.viewmodel.ViewModelUnit
import kotlinx.android.synthetic.main.fragment_unit.*
import javax.inject.Inject

class FragmentUnit : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_unit
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Unit"

    private lateinit var viewModel: ViewModelUnit

    @Inject
    lateinit var adapter: AdapterUnit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserver()
        initRecyclerView()
        initNavigation()

    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelUnit::class.java)
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            val bundle = bundleOf("data" to "some hard code string type data")
            /*findNavController().navigateWithAnim(
                //FragmentDirections.R.id.action_fragmentActivities_to_fragmentAddActivities,
                //bundle
            )*/
        }
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

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun updateCollection() {
        //adapter.setData()
    }

}
