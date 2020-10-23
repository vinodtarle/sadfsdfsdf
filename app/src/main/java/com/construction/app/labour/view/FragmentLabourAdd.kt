package com.construction.app.labour.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentLabourAddBinding
import com.construction.app.labour.model.Labour
import com.construction.app.labour.viewmodel.ViewModelLabour
import kotlinx.android.synthetic.main.fragment_labour_add.*

class FragmentLabourAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_labour_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Labour"

    private lateinit var viewModel: ViewModelLabour
    private lateinit var binding: FragmentLabourAddBinding
    private lateinit var labour: Labour

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentLabourAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindRateType()
        initListener()

        setTitle(getString(R.string.titleLabourAdd))
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelLabour::class.java)
        this.labour = Labour()
        this.binding.labour = this.labour
    }

    private fun bindRateType() {
        val values = listOf("Per Day", "Monthly")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            values
        )
        rateType.setAdapter(adapter)
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

        rateType.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                labour.rateType = parent.getItemAtPosition(position) as String
            }

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun validate(): Boolean {
        return (this.labour.name.isNotBlank()
                && this.labour.contact.isNotBlank()
                && this.labour.rate.isNotBlank()
                && this.labour.rateType.isNotBlank()
                )
    }

    private fun addDocument() {
        buttonAdd.isEnabled = false
        this.viewModel.set(this.labour)
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
        this.labour = Labour()
        this.binding.labour = this.labour
    }
}

