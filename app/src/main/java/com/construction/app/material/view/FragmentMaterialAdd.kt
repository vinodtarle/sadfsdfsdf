package com.construction.app.material.view

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
import com.construction.app.databinding.FragmentMaterialAddBinding
import com.construction.app.item.model.Item
import com.construction.app.item.viewmodel.ViewModelItem
import com.construction.app.material.model.Material
import com.construction.app.material.viewmodel.ViewModelMaterial
import com.construction.app.site.model.Site
import com.construction.app.site.viewmodel.ViewModelSite
import com.construction.app.supplier.model.Supplier
import com.construction.app.supplier.viewmodel.ViewModelSupplier
import com.construction.app.unit.model.Unit
import com.construction.app.unit.viewmodel.ViewModelUnit
import kotlinx.android.synthetic.main.fragment_material_add.*

class FragmentMaterialAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_material_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Material"

    private lateinit var binding: FragmentMaterialAddBinding
    private lateinit var viewModelMaterial: ViewModelMaterial
    private lateinit var viewModelItem: ViewModelItem
    private lateinit var viewModelUnit: ViewModelUnit
    private lateinit var viewModelSite: ViewModelSite
    private lateinit var viewModelSupplier: ViewModelSupplier

    private lateinit var material: Material

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentMaterialAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
        getItem()
        getUnit()
        getSupplier()
        getSite()

        setTitle(R.string.titleMaterialAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModelMaterial = ViewModelProvider(this, factory).get(ViewModelMaterial::class.java)
        this.viewModelItem = ViewModelProvider(this, factory).get(ViewModelItem::class.java)
        this.viewModelUnit = ViewModelProvider(this, factory).get(ViewModelUnit::class.java)
        this.viewModelSite = ViewModelProvider(this, factory).get(ViewModelSite::class.java)
        this.viewModelSupplier = ViewModelProvider(this, factory).get(ViewModelSupplier::class.java)

        this.material = Material()
        this.binding.material = this.material
    }

    private fun initListener() {
        edDate.setOnClickListener {
            hideKeyboard()
            datePickerDialog { date ->
                this.material.date = date
            }
        }
        item.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                material.item = parent.getItemAtPosition(position) as Item
            }
        unit.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                material.unit = parent.getItemAtPosition(position) as Unit
            }
        supplier.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                material.supplier = parent.getItemAtPosition(position) as Supplier
            }
        site.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                material.site = parent.getItemAtPosition(position) as Site
            }

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun getItem() {
        this.viewModelItem.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindItem(
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

    private fun bindItem(collection: List<Item>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.item.setAdapter(adapter)
    }

    private fun getUnit() {
        this.viewModelUnit.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindUnit(
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

    private fun bindUnit(collection: List<Unit>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.unit.setAdapter(adapter)
    }

    private fun getSupplier() {
        this.viewModelSupplier.collectionListener()
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
                        showErrorLoad()
                    }
                }
            })
    }

    private fun bindSupplier(collection: List<Supplier>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.supplier.setAdapter(adapter)
    }

    private fun getSite() {
        this.viewModelSite.collectionListener()
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
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.site.setAdapter(adapter)
    }

    private fun validate(): Boolean {
        return (this.material.date.isNotBlank()
                && this.material.item != null
                && this.material.quantity.isNotBlank()
                && this.material.rate.isNotBlank()
                && this.material.unit != null
                && this.material.supplier != null
                && this.material.site != null
                )
    }

    private fun addDocument() {
        buttonAdd.isEnabled = false
        this.viewModelMaterial.set(data = this.material)
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
        this.material = Material()
        this.binding.material = this.material
    }
}
