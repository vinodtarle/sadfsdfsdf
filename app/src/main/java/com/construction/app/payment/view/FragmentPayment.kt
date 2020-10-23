package com.construction.app.payment.view

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
import com.construction.app.payment.adapter.AdapterPayment
import com.construction.app.payment.model.Payment
import com.construction.app.payment.viewmodel.ViewModelPayment
import kotlinx.android.synthetic.main.fragment_payment.*
import javax.inject.Inject

class FragmentPayment internal constructor(
    private val isReport: Boolean = false,
    private val isSite: Boolean = false,
    private val isSupplier: Boolean = false,
    private val isLabour: Boolean = false,
    private val document: BaseModule
) : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_payment
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Payment"

    private lateinit var viewModel: ViewModelPayment

    @Inject
    lateinit var adapter: AdapterPayment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(if (isReport) R.string.strDetails else R.string.titlePayment)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        buttonAdd.visibility = if (isReport) View.GONE else View.VISIBLE
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelPayment::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentPaymentDirections.toFragmentPaymentAdd()
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

    private fun updateCollection(collection: List<Payment>) {
        this.adapter.setCollection(collection = collection)
    }
}
