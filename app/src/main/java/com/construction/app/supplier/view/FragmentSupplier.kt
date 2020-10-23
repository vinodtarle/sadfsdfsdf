package com.construction.app.supplier.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.supplier.adapter.AdapterSupplier
import com.construction.app.supplier.model.Supplier
import com.construction.app.supplier.viewmodel.ViewModelSupplier
import kotlinx.android.synthetic.main.fragment_supplier.*
import javax.inject.Inject

class FragmentSupplier : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_supplier
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Supplier"

    private lateinit var viewModel: ViewModelSupplier

    @Inject
    lateinit var adapter: AdapterSupplier

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(R.string.titleSupplier)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelSupplier::class.java)
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentSupplierDirections.toFragmentSupplierAdd()
            )
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initObserver() {
        this.viewModel.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        updateCollection(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showFullScreenError()
                    }
                }
            })
    }

    private fun updateCollection(collection: List<Supplier>) {
        this.adapter.setCollection(baseFragment = this, collection = collection)
    }

    fun navigateToDetails(document: Supplier) {
        toNavigate(R.id.fragmentDetails, document)
    }
}
