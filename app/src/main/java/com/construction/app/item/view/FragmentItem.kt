package com.construction.app.item.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.view.BaseFragment
import com.construction.app.item.adapter.AdapterItem
import com.construction.app.item.viewmodel.ViewModelItem
import kotlinx.android.synthetic.main.fragment_item.*

class FragmentItem : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_item
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Items"

    private lateinit var viewModel: ViewModelItem
    private lateinit var adapter: AdapterItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserver()
        initRecyclerView()
        initNavigation()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelItem::class.java)
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
//            val bundle = bundleOf("data" to "some hard code string type data")
//            findNavController().navigateWithAnim(
//                FragmentItemDirections.actionFragmentItemToFragmentItemAdd()
//                //R.id.action_fragmentItem_to_fragmentItemAdd
//            )
        }
    }

    private fun initObserver() {
        this.viewModel.document("")
//            .observe(viewLifecycleOwner, Observer {
//                when (result) {
//                    is Resource.Loading -> {
//                        showProgressBar()
//                    }
//                    is Resource.Success -> {
//                        updateCollection()
//                    }
//                    is Resource.Error -> {
//                        hideProgressBar()
//                        showFullScreenError()
//                    }
//                }
//            })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    fun updateCollection() {
        //adapter.setData()
    }
}
