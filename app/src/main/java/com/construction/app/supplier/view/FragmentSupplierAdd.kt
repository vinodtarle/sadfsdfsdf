package com.construction.app.supplier.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentSupplierAddBinding
import com.construction.app.supplier.model.Supplier
import com.construction.app.supplier.viewmodel.ViewModelSupplier
import kotlinx.android.synthetic.main.fragment_labour_add.edContact
import kotlinx.android.synthetic.main.fragment_labour_add.edName
import kotlinx.android.synthetic.main.fragment_supplier_add.*

class FragmentSupplierAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_supplier_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Supplier"

    private lateinit var viewModel: ViewModelSupplier

    private lateinit var binding: FragmentSupplierAddBinding
    private lateinit var supplier: Supplier

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentSupplierAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()

        setTitle(R.string.titleSupplierAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelSupplier::class.java)
        this.supplier = Supplier()
        this.binding.supplier = this.supplier
    }

    private fun initListener() {
        edName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validate()
            }
        })

        edContact.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validate()
            }
        })

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun validate(): Boolean {
        return this.supplier.name.isNotEmpty() && this.supplier.contact.isNotEmpty()
    }

    private fun addDocument() {
        this.viewModel.set(this.supplier)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        clear()
                        hideProgressBar()
                        showSuccessAdd()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorAdd()
                    }
                }
            })
    }

    private fun clear() {
        this.supplier = Supplier()
        this.binding.supplier = this.supplier
    }
}
