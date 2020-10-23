package com.construction.app.labour.view

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
import com.construction.app.labour.adapter.AdapterLabour
import com.construction.app.labour.model.Labour
import com.construction.app.labour.viewmodel.ViewModelLabour
import kotlinx.android.synthetic.main.fragment_labour.*
import javax.inject.Inject

class FragmentLabour : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_labour
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Labour"

    private lateinit var viewModel: ViewModelLabour

    @Inject
    lateinit var adapter: AdapterLabour

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(R.string.titleLabour)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelLabour::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentLabourDirections.toFragmentLabourAdd()
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

    private fun updateCollection(collection: List<Labour>) {
        this.adapter.setCollection(baseFragment = this, collection = collection)
    }

    fun navigateToDetails(document: Labour) {
        toNavigate(R.id.fragmentDetails, document)
    }
}