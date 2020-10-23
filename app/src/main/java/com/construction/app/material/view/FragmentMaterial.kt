package com.construction.app.material.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.material.adapter.AdapterMaterial
import com.construction.app.material.model.Material
import com.construction.app.material.viewmodel.ViewModelMaterial
import kotlinx.android.synthetic.main.fragment_material.*
import javax.inject.Inject

class FragmentMaterial internal constructor(
    private val isReport: Boolean = false,
    private val isSite: Boolean = false,
    private val isSupplier: Boolean = false,
    private val isLabour: Boolean = false,
    private val document: BaseModule
) : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_material
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Material"

    private lateinit var viewModel: ViewModelMaterial

    @Inject
    lateinit var adapter: AdapterMaterial

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(if (isReport) R.string.strDetails else R.string.titleMaterial)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        buttonAdd.visibility = if (isReport) View.GONE else View.VISIBLE
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelMaterial::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentMaterialDirections.toFragmentMaterialAdd()
            )
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

    private fun updateCollection(collection: List<Material>) {
        this.adapter.setCollection(collection = collection)
    }
}
