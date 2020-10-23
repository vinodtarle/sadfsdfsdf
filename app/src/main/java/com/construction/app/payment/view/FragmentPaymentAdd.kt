package com.construction.app.payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentPaymentAddBinding
import com.construction.app.payment.model.Payment
import com.construction.app.payment.viewmodel.ViewModelPayment
import com.construction.app.site.model.Site
import com.construction.app.site.viewmodel.ViewModelSite
import com.construction.app.supplier.model.Supplier
import com.construction.app.supplier.viewmodel.ViewModelSupplier
import kotlinx.android.synthetic.main.fragment_payment_add.*

class FragmentPaymentAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_payment_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Payment"

    private lateinit var binding: FragmentPaymentAddBinding
    private lateinit var viewModelPayment: ViewModelPayment
    private lateinit var viewModelSite: ViewModelSite
    private lateinit var viewModelSupplier: ViewModelSupplier

    private lateinit var payment: Payment
    private var site: Site? = null
    private var supplier: Supplier? = null
    private var sites = ArrayList<Site>()
    private var suppliers = ArrayList<Supplier>()
    private var callbacks: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentPaymentAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
        getSite()
        getSupplier()

        setTitle(R.string.titlePaymentAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModelPayment = ViewModelProvider(this, factory).get(ViewModelPayment::class.java)
        this.viewModelSite = ViewModelProvider(this, factory).get(ViewModelSite::class.java)
        this.viewModelSupplier = ViewModelProvider(this, factory).get(ViewModelSupplier::class.java)
        this.payment = Payment()
        //this.payment.date = getCurrentDate()
        this.binding.payment = this.payment
    }

    private fun initListener() {
        edDate.setOnClickListener {
            hideKeyboard()
            datePickerDialog { date ->
                this.payment.date = date
            }
        }

        paymentFor.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position) as String

            println(">>>>> find user $item")

            setSelectedItem(item, position)
        }

        rgPaymentStatus.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbReceived -> {
                    this.payment.paymentStatus = getString(R.string.strReceived)
                }
                R.id.rbSend -> {
                    this.payment.paymentStatus = getString(R.string.strSend)
                }
            }
            this.binding.payment = this.payment
        }

        rgPaymentType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbCash -> {
                    this.payment.paymentType = getString(R.string.strCash)
                }
                R.id.rbCheque -> {
                    this.payment.paymentType = getString(R.string.strCheque)
                }
                R.id.rbOnline -> {
                    this.payment.paymentType = getString(R.string.strOnline)
                }
            }
            this.binding.payment = this.payment
        }

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                //addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun setSelectedItem(item: String, position: Int) {
        this.site = null
        this.supplier = null
        var hasSite = false
        var hasSupplier = false

        if (this.sites.size <= this.suppliers.size) {
            this.sites.forEach { site ->
                if (site.name == item) {
                    hasSite = true
                    if (position < this.sites.size) this.site = this.sites[position]
                    else this.site = this.sites[position - this.suppliers.size]
                }
            }
            if (!hasSite) this.suppliers.forEach { supplier ->
                if (supplier.name == item) {
                    if (position < this.suppliers.size) this.supplier = this.suppliers[position]
                    else this.supplier = this.suppliers[position - this.sites.size]
                }
            }
        } else {
            this.suppliers.forEach { supplier ->
                if (supplier.name == item) {
                    hasSupplier = true
                    if (position < this.suppliers.size) this.supplier = this.suppliers[position]
                    else this.supplier = this.suppliers[position - this.sites.size]
                }
            }
            if (!hasSupplier) {
                this.sites.forEach { site ->
                    if (site.name == item) {
                        if (position < this.sites.size) this.site = this.sites[position]
                        else this.site = this.sites[position - this.suppliers.size]
                    }
                }
            }
        }

        this.payment.site = this.site
        this.payment.supplier = this.supplier
    }

    private fun getSite() {
        this.viewModelSite.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindSite(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorLoad()
                    }
                }
            })
    }

    private fun bindSite(collection: List<Site>) {
        ++this.callbacks
        this.sites.addAll(collection)
        if (this.callbacks > 1)
            bindPaymentReceiver()
    }

    private fun getSupplier() {
        this.viewModelSupplier.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindSupplier(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorAdd()
                    }
                }
            })
    }

    private fun bindSupplier(collection: List<Supplier>) {
        ++this.callbacks
        this.suppliers.addAll(collection)
        if (this.callbacks > 1)
            bindPaymentReceiver()
    }

    private fun bindPaymentReceiver() {
        val data = ArrayList<String>()
        this.suppliers.forEach {
            data.add(it.name)
        }
        this.sites.forEach {
            data.add(it.name)
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu, data)
        this.binding.paymentFor.setAdapter(adapter)
    }

    private fun validate(): Boolean {
        return ((this.payment.site != null || this.supplier != null)
                && this.payment.date.isNotBlank()
                && this.payment.amount.isNotBlank()
                )
    }

    private fun addDocument() {
        buttonAdd.isEnabled = false
        this.viewModelPayment.set(this.payment)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        clear()
                        hideProgressBar()
                        showSuccessAdd()
                        onBackPress()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorAdd()
                    }
                }
            })
    }

    private fun clear() {
        this.payment = Payment()
        this.binding.payment = this.payment
    }
}
