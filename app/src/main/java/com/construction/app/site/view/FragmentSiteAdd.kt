package com.construction.app.site.view

import android.app.DatePickerDialog
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
import com.construction.app.base.constant.Constant
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentSiteAddBinding
import com.construction.app.site.model.Site
import com.construction.app.site.viewmodel.ViewModelSite
import kotlinx.android.synthetic.main.fragment_site_add.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentSiteAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_site_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Site"

    private lateinit var viewModel: ViewModelSite
    private lateinit var binding: FragmentSiteAddBinding
    private lateinit var site: Site

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentSiteAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindSiteType()
        bindRateType()
        initListener()

        setTitle(R.string.titleSite)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelSite::class.java)
        this.site = Site()
        this.binding.site = this.site
    }

    private fun bindSiteType() {
        val values = listOf("House", "Banglo", "Row House", "Building")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            values
        )
        siteType.setAdapter(adapter)
    }

    private fun bindRateType() {
        val values = listOf("Square feet", "Package")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            values
        )
        rateType.setAdapter(adapter)
    }

    private fun initListener() {
        edStartDate.setOnClickListener {
            hideKeyboard()
            datePickerDialog { date ->
                this.site.startDate = date
            }
        }

        edStartDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.edDeliveryDate.isEnabled = true
                site.deliveryDate = Constant.BLANK
                binding.site = site
            }
        })

        edDeliveryDate.setOnClickListener {
            hideKeyboard()
            deliveryDate(it)
        }
        edDeliveryDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
            }
        })

        siteType.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                site.siteType = parent.getItemAtPosition(position) as String
            }

        rateType.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                site.rateType = parent.getItemAtPosition(position) as String
            }

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun toTime(date: String): Long {
        return SimpleDateFormat("dd/MM/yyyy").parse(date).time
    }

    private fun deliveryDate(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, y, m, d ->
                site.deliveryDate = d.toString() + "/" + (m + 1) + "/" + y
                binding.site = site
            }, year, month, day
        )
        dialog.datePicker.minDate = toTime(site.startDate)
        dialog.show()
    }

    private fun validate(): Boolean {
        return (this.site.name.isNotBlank()
                && this.site.contact.isNotBlank()
                && this.site.address.isNotBlank()
                && this.site.siteType.isNotBlank()
                && this.site.startDate.isNotBlank()
                && this.site.deliveryDate.isNotBlank()
                && this.site.rate.isNotBlank()
                && this.site.rateType.isNotBlank()
                )
    }

    private fun addDocument() {
        buttonAdd.isEnabled = false
        this.viewModel.set(this.site)
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
        this.site = Site()
        this.binding.site = this.site
    }
}
