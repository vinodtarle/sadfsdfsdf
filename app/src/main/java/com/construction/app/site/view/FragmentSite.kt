package com.construction.app.site.view

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
import com.construction.app.site.adapter.AdapterSite
import com.construction.app.site.model.Site
import com.construction.app.site.viewmodel.ViewModelSite
import kotlinx.android.synthetic.main.fragment_site.*
import javax.inject.Inject

class FragmentSite : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_site
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Site"

    private lateinit var viewModel: ViewModelSite

    @Inject
    lateinit var adapter: AdapterSite

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(R.string.titleSite)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelSite::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentSiteDirections.toFragmentSiteAdd()
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

    private fun updateCollection(collection: List<Site>) {
        this.adapter.setCollection(baseFragment = this, collection = collection)
    }

    fun navigateToDetails(document: Site) {
        toNavigate(R.id.fragmentDetails, document)
    }
}
