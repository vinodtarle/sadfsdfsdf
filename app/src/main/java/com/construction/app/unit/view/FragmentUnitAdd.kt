package com.construction.app.unit.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.constant.Constant
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentUnitAddBinding
import com.construction.app.unit.adapter.AdapterUnit
import com.construction.app.unit.model.Unit
import com.construction.app.unit.viewmodel.ViewModelUnit
import kotlinx.android.synthetic.main.fragment_unit_add.*
import java.util.*
import javax.inject.Inject

class FragmentUnitAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_unit_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Unit"

    private lateinit var viewModel: ViewModelUnit
    private lateinit var binding: FragmentUnitAddBinding

    @Inject
    lateinit var adapter: AdapterUnit

    private lateinit var unit: Unit
    private lateinit var ui: UI
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentUnitAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initObserver()
        initListener()

        setTitle(R.string.titleUnitAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelUnit::class.java)
        this.ui = UI()
        this.unit = Unit()
        this.binding.ui = this.ui
        this.binding.unit = this.unit
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initObserver() {
        this.viewModel.collectionListener()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        updateCollection(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                        hideProgressBar()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showFullScreenError()
                    }
                }
            })
        this.viewModel.update.nonNull().observe(viewLifecycleOwner, Observer {
            ui.update = it
        })
    }

    private fun updateCollection(collection: List<Unit>) {
        this.adapter.setCollection(collection = collection)
    }

    private fun initListener() {
        edUnit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                unit.status = !s.isNullOrBlank()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        ivCancel.setOnClickListener { clear() }

        buttonAdd.setOnClickListener {
            hideKeyboard()
            if (validate()) {
                if (!isUpdate) {
                    this.unit.id = Constant.BLANK
                    this.unit.createdAt = Date()
                    this.unit.createdBy = "Admin"
                    addDocument()
                } else updateUnit()
            } else showErrorInput("Enter unit name!!")
        }
    }

    private fun validate(): Boolean {
        return this.unit.name.isNotBlank()
    }

    private fun addDocument() {
        this.viewModel.set(data = this.unit)
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

    fun editItem(item: Unit) {
        this.ui.update = true
        this.isUpdate = true
        val it = item
        this.unit = it
    }

    private fun updateUnit() {
        this.viewModel.update(
            documentId = this.unit.id,
            key = getString(R.string.fieldItem),
            value = this.unit.name
        ).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    clear()
                    buttonAdd.isEnabled = true
                    hideProgressBar()
                    showSuccessAdd()
                }
                is Resource.Error -> {
                    clear()
                    hideProgressBar()
                    showErrorAdd()
                }
            }
        })
    }

    private fun clear() {
        this.isUpdate = false
        this.ui.update = false
        this.unit = Unit()
        this.binding.unit = this.unit
    }
}